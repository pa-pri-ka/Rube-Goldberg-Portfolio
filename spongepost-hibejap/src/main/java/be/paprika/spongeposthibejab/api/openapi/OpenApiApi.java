package be.paprika.spongeposthibejab.api.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.openapitools.codegen.ClientOptInput;
import org.openapitools.codegen.languages.SpringCodegen;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * @noinspection HardcodedFileSeparator
 */
@RestController
@RequestMapping(path = "/api/openapi")
public class OpenApiApi {

	private final ResourceLoader resourceLoader;

	OpenApiApi(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@GetMapping(path = "/generate", produces = APPLICATION_JSON_VALUE)
	public GeneratedOpenApiFiles generateStubsFromOAD() throws IOException {
		// Load spec
		Resource specDocResource = resourceLoader.getResource("classpath:public/openapi.yaml");
		String spec = specDocResource.getContentAsString(StandardCharsets.UTF_8);

		SwaggerParseResult parseResult = new OpenAPIV3Parser().readContents(spec, null, null);
		OpenAPI openApi = parseResult.getOpenAPI();
		if (null == openApi) {
			throw new RuntimeException("Failed to parse OpenAPI spec");
		}

		// Create generator config directly
		SpringCodegen config = new SpringCodegen();
		config.setOpenAPI(openApi);
		config.setOutputDir(System.getProperty("java.io.tmpdir"));
		config.setModelPackage("spongepost.hibejab.model");
		config.setApiPackage("spongepost.hibejab.api");
		config.setInvokerPackage("spongepost.hibejab.client");
		// Set additional properties
		config.additionalProperties().put("interfaceOnly", "true");
		config.additionalProperties().put("useSpringBoot3", "true");
		config.additionalProperties().put("dateLibrary", "java8");

		ClientOptInput opts = new ClientOptInput();
		opts.config(config);
		opts.openAPI(openApi);
		InMemGenerator inMemGenerator = new InMemGenerator();
		inMemGenerator.opts(opts);

		inMemGenerator.generate();
		Map<String, String> files = inMemGenerator.getFiles();
		return new GeneratedOpenApiFiles(files);
	}
}

