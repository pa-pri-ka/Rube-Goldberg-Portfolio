<#
.SYNOPSIS
    Checks local Docker images against their remote registry for updates.
.EXAMPLE
    .\docker-update-check.ps1                  # check ALL local images
    .\docker-update-check.ps1 nginx,redis     # check specific images
#>
[CmdletBinding()]
param(
    [Parameter(Position = 0)]
    [string[]]$Images
)

# 'Continue' instead of 'Stop': native (docker.exe) stderr must not kill the script
$ErrorActionPreference = 'Continue'

function Test-DockerRunning {
    docker info *> $null
    if ($LASTEXITCODE -ne 0) {
        Write-Host 'ERROR: Docker daemon is not running or not accessible.' -ForegroundColor Red
        exit 1
    }
}

# Local digest from RepoDigests, e.g. "nginx@sha256:abc..." -> "sha256:abc..."
function Get-LocalDigest {
    param([string]$FullRef)

    $raw = docker image inspect $FullRef --format '{{json .RepoDigests}}' 2>$null
    if ($LASTEXITCODE -ne 0 -or -not $raw) { return $null }

    # Take first digest; strip everything up to and including '@'
    $first = ($raw | ConvertFrom-Json) | Select-Object -First 1
    if ($first -and $first -match '@(.+)$') {
        return $Matches[1]
    }
    return $null
}

# Remote digest for the tag, without pulling.
# Primary: docker buildx imagetools (returns the manifest-list digest, which
# is exactly what 'docker pull' records in RepoDigests).
# Fallback : docker manifest inspect -v (single-manifest images).
function Get-RemoteDigest {
    param([string]$FullRef)

    $out = docker buildx imagetools inspect $FullRef --format '{{.Manifest.Digest}}' 2>$null
    if ($LASTEXITCODE -eq 0 -and $out) { return $out.Trim() }

    $json = docker manifest inspect -v $FullRef 2>$null
    if ($LASTEXITCODE -ne 0 -or -not $json) { return $null }

    try {
        $manifest = $json | ConvertFrom-Json
        $desc = @($manifest.Descriptor)[0]
        if ($desc.digest) { return $desc.digest }
    } catch { }
    return $null
}

function Test-ImageUpdate {
    param([string]$FullRef)

    # Confirm the image exists locally (repo:tag comes from docker images)
    docker image inspect $FullRef *> $null
    if ($LASTEXITCODE -ne 0) {
        Write-Host ('IMAGE NOT FOUND LOCALLY: {0}' -f $FullRef) -ForegroundColor Red
        return $false
    }

    $localDigest  = Get-LocalDigest  -FullRef $FullRef
    $remoteDigest = Get-RemoteDigest -FullRef $FullRef

    if (-not $localDigest -or -not $remoteDigest) {
        Write-Host ('{0,-45} {1,-17} {2,-17} ' -f $FullRef, 'N/A', 'N/A') -NoNewline
        Write-Host 'CANNOT COMPARE (no registry access or locally-built image)' -ForegroundColor Yellow
        return $false
    }

    $shortLocal  = $localDigest.Substring(0, 15)
    $shortRemote = $remoteDigest.Substring(0, 15)

    Write-Host ('{0,-45} {1,-17} {2,-17} ' -f $FullRef, $shortLocal, $shortRemote) -NoNewline

    if ($localDigest -eq $remoteDigest) {
        Write-Host 'OK - UP TO DATE' -ForegroundColor Green
        return $false
    } else {
        # Red line plus an explicit marker, so it stays visible even
        # where color is lost (redirected output, CI logs, etc.)
        Write-Host ('*** UPDATE AVAILABLE ***') -ForegroundColor Red
        return $true
    }
}

# ------------------------- Main -------------------------

Test-DockerRunning

Write-Host ''
Write-Host 'DOCKER IMAGE UPDATE CHECKER' -ForegroundColor Cyan
Write-Host ''

if ($Images -and $Images.Count -gt 0) {
    $targets = $Images
} else {
    $targets = @(docker images --format '{{.Repository}}:{{.Tag}}' | Where-Object { $_ -and $_ -notmatch '^<none>' })
}

if (-not $targets -or $targets.Count -eq 0) {
    Write-Host 'No Docker images found locally.'
    exit 0
}

# Table header
Write-Host ('{0,-45} {1,-17} {2,-17} {3}' -f 'IMAGE', 'LOCAL DIGEST', 'REMOTE DIGEST', 'STATUS') -ForegroundColor White
Write-Host ('-' * 110)

$updateCount = 0
foreach ($ref in $targets) {
    $ref = $ref.Trim()
    if (-not $ref) { continue }
    if (Test-ImageUpdate -FullRef $ref) { $updateCount++ }
}

Write-Host ('-' * 110)
Write-Host ('Scan complete: {0} image(s) checked, {1} update(s) available.' -f $targets.Count, $updateCount)
Write-Host "Run 'docker pull <image>' to apply available updates."
