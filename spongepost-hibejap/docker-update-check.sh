#!/bin/bash
# docker-update-check.sh - Check Docker image updates on Linux
# Usage: ./docker-update-check.sh [image ...]   (no args = check all local images)

set -uo pipefail

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[0;33m'
BOLD='\033[1m'
NC='\033[0m'

color_ok() {
    # Colors work only on a TTY; otherwise fall back to plain text
    [ -t 1 ]
}

print_colored() {
    local message="$1" color="$2"
    if color_ok; then
        printf '%b%s%b\n' "$color" "$message" "$NC"
    elif [ "$color" = "$RED" ]; then
        # Fallback visible marker when colors are unavailable
        echo "[UPDATE AVAILABLE] $message"
    else
        echo "$message"
    fi
}

# Get local digest from RepoDigests (e.g. "nginx@sha256:abc..." -> "sha256:abc...")
get_local_digest() {
    local ref="$1"
    local digests
    digests=$(docker image inspect "$ref" --format '{{json .RepoDigests}}' 2>/dev/null)
    echo "$digests" | grep -o '@sha256:[a-f0-9]*' | head -1 | tr -d '@'
}

# Get remote digest for the tag, without pulling
get_remote_digest() {
    local ref="$1"
    local out
    # Preferred: manifest-list digest via buildx (matches what pull stores)
    out=$(docker buildx imagetools inspect "$ref" --format '{{.Manifest.Digest}}' 2>/dev/null)
    if [ -n "$out" ]; then
        echo "$out"
        return
    fi
    # Fallback: verbose manifest inspection
    docker manifest inspect -v "$ref" 2>/dev/null \
        | grep -o '"digest": *"[^"]*"' | head -1 | cut -d'"' -f4
}

check_image() {
    local full_ref="$1"   # format: repository:tag

    local repo="${full_ref%:*}"
    local tag="${full_ref##*:}"

    local local_digest remote_digest

    if ! docker image inspect "$full_ref" >/dev/null 2>&1; then
        print_colored "IMAGE NOT FOUND LOCALLY: $full_ref" "$RED"
        return 1
    fi

    local_digest=$(get_local_digest "$full_ref")
    remote_digest=$(get_remote_digest "$full_ref")

    if [ -z "$local_digest" ] || [ -z "$remote_digest" ]; then
        printf '%-45s %-17s %-17s ' "$full_ref" "N/A" "N/A"
        print_colored "CANNOT COMPARE (no registry access or locally-built image)" "$YELLOW"
        return 1
    fi

    local short_local="${local_digest:0:15}"
    local short_remote="${remote_digest:0:15}"

    printf '%-45s %-17s %-17s ' "$full_ref" "$short_local" "$short_remote"
    if [ "$local_digest" = "$remote_digest" ]; then
        print_colored "OK - UP TO DATE" "$GREEN"
        return 0
    else
        print_colored "*** UPDATE AVAILABLE ***" "$RED"
        return 2
    fi
}

# ---- Main ----

if ! docker info >/dev/null 2>&1; then
    echo "ERROR: Docker daemon is not running or not accessible." >&2
    exit 1
fi

printf '\n%bDOCKER IMAGE UPDATE CHECKER%b\n\n' "$BOLD" "$NC"

updates=0
checked=0

printf '%-45s %-17s %-17s %s\n' "IMAGE" "LOCAL DIGEST" "REMOTE DIGEST" "STATUS"
printf '%s\n' "$(printf '%.0s-' $(seq 1 110))"

if [ $# -gt 0 ]; then
    targets=("$@")
else
    mapfile -t targets < <(docker images --format '{{.Repository}}:{{.Tag}}' | grep -v '<none>')
fi

if [ ${#targets[@]} -eq 0 ]; then
    echo "No Docker images found locally."
    exit 0
fi

for ref in "${targets[@]}"; do
    [ "$ref" = "<none>" ] && continue
    checked=$((checked + 1))
    check_image "$ref"
    rc=$?
    [ $rc -eq 2 ] && updates=$((updates + 1))
done

printf '%s\n' "$(printf '%.0s-' $(seq 1 110))"
echo "Scan complete: $checked image(s) checked, $updates update(s) available."
echo "Run 'docker pull <image>' to apply available updates."
