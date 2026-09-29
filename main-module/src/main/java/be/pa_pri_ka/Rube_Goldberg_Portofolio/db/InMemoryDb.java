package be.pa_pri_ka.Rube_Goldberg_Portofolio.db;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
class InMemoryDb {

	private final DataSource dataSource;
	private final JdbcClient jdbcClient;

	InMemoryDb(final DataSource dataSource, final JdbcClient jdbcClient) {
		this.dataSource = dataSource;
		this.jdbcClient = jdbcClient;
	}
}
