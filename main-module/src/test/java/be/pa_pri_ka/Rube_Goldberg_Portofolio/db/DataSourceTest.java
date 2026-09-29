package be.pa_pri_ka.Rube_Goldberg_Portofolio.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DataSourceTest {

	@Autowired
	private DataSource dataSource;

	@Test
	void hasAValidDataSourceConfigured() {
		assertThat(this.dataSource).isNotNull();
	}
}
