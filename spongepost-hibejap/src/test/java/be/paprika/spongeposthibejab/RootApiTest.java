package be.paprika.spongeposthibejab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RootApiTest {

	@Test
	void returnsHi(@Autowired RestTestClient restClient) {
		restClient.get().uri("/api/hi").exchange().expectStatus().isOk();
	}
}
