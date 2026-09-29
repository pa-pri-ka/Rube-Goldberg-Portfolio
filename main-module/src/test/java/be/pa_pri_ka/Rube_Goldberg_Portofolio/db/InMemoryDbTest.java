package be.pa_pri_ka.Rube_Goldberg_Portofolio.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InMemoryDbTest {

	@Autowired
	private JdbcClient jdbcClient;

	@Test
	void executesTheScriptToBuildTheSchemas() {
		//noinspection SqlResolve
		final Integer count = this.jdbcClient.sql("select count(*) from beans")
				.query(Integer.class)
				.single();
		assertThat(count).isEqualTo(0);
	}
}
