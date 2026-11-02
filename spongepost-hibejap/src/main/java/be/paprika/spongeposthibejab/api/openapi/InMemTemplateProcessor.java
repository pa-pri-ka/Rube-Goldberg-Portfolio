package be.paprika.spongeposthibejab.api.openapi;

import org.openapitools.codegen.TemplateManager;
import org.openapitools.codegen.api.TemplatePathLocator;
import org.openapitools.codegen.api.TemplatingEngineAdapter;
import org.openapitools.codegen.templating.TemplateManagerOptions;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class InMemTemplateProcessor extends TemplateManager {

	private final Map<String, byte[]> files = new LinkedHashMap<>();

	/**
	 * Constructs a new instance of a {@link TemplateManager}
	 *
	 * @param options The {@link TemplateManagerOptions} for reading and writing templates
	 * @param engineAdapter The adaptor to underlying templating engine
	 * @param templateLoaders Loaders which define where we look for templates
	 */
	InMemTemplateProcessor(TemplateManagerOptions options, TemplatingEngineAdapter engineAdapter, TemplatePathLocator[] templateLoaders) {
		super(options, engineAdapter, templateLoaders);
	}

	Map<String, byte[]> getFiles() {
		return files;
	}

	@Override
	public File writeToFile(String filename, String contents) {
		return this.writeToFile(filename, contents.getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public File writeToFile(String filename, byte[] contents) {
		String key = Path.of(filename).toAbsolutePath().normalize().toString();
		files.put(key, contents);
		return new File("");
	}
}
