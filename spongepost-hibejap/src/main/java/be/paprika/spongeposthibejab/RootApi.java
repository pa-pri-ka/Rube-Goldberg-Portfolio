package be.paprika.spongeposthibejab;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.TEXT_PLAIN_VALUE;

/** @noinspection HardcodedFileSeparator*/
@RestController
@RequestMapping(path = "/api", produces = APPLICATION_JSON_VALUE)
public class RootApi {

	@GetMapping(path = "/hi", produces = TEXT_PLAIN_VALUE)
	public String hi() {
		return "hello";
	}
}
