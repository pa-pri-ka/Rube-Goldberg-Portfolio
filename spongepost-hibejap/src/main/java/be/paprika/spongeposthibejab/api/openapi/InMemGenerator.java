package be.paprika.spongeposthibejab.api.openapi;

import org.openapitools.codegen.ClientOptInput;
import org.openapitools.codegen.DefaultGenerator;
import org.openapitools.codegen.Generator;
import org.openapitools.codegen.api.TemplatePathLocator;
import org.openapitools.codegen.api.TemplatingEngineAdapter;
import org.openapitools.codegen.templating.CommonTemplateContentLocator;
import org.openapitools.codegen.templating.GeneratorTemplateContentLocator;
import org.openapitools.codegen.templating.TemplateManagerOptions;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class InMemGenerator extends DefaultGenerator {

	InMemGenerator() {
		this(false);
	}

	private InMemGenerator(Boolean dryRun) {
		super(dryRun);
		setGenerateMetadata(false);
	}

	Map<String, String> getFiles() {
		Map<String, String> out = new LinkedHashMap<>();
		if (this.templateProcessor instanceof InMemTemplateProcessor processor) {
			processor.getFiles().forEach((path, bytes) -> out.put(path, new String(bytes, StandardCharsets.UTF_8)));
		}
		return out;
	}

	@Override
	public Generator opts(ClientOptInput opts) {
		Generator generator = super.opts(opts);
		// Override template processor instance to use InMemTemplateProcessor
		TemplateManagerOptions templateManagerOptions = new TemplateManagerOptions(this.config.isEnableMinimalUpdate(), this.config.isSkipOverwrite());
		TemplatingEngineAdapter templatingEngine = this.config.getTemplatingEngine();
		TemplatePathLocator commonTemplateLocator = new CommonTemplateContentLocator();
		TemplatePathLocator generatorTemplateLocator = new GeneratorTemplateContentLocator(this.config);
		this.templateProcessor = new InMemTemplateProcessor(
				templateManagerOptions,
				templatingEngine,
				new TemplatePathLocator[]{generatorTemplateLocator, commonTemplateLocator}
		);
		return generator;
	}
}
