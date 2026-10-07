package be.pa_pri_ka.Rube_Goldberg_Portofolio.jpa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CompanyTransactionsITest {

	@Autowired
	private CompanyTransactions companyTransactions;
	@Autowired
	private JdbcClient jdbcClient;

	@AfterEach
	void setUp() {
		JdbcTestUtils.deleteFromTables(jdbcClient, "employee", "recruiter", "company", "address");
	}

	@Test
	void addsACompany() {
		Company company = new Company();
		company.setName("Company Name");

		companyTransactions.add(company);

		final int rowsCount = JdbcTestUtils.countRowsInTable(jdbcClient, "company");
		assertEquals(1, rowsCount);
	}

	@Test
	void addsACompanyAndAddress() {
		Company company = new Company();
		company.setName("Company Name");
		Address address = new Address();
		address.setCity("Brussels");
		address.setCountry("Belgium");
		address.setNumber((short) 123);
		address.setStreet("Rue de JeSaisPas");
		address.setZipcode("1000");
		company.setAddress(address);

		companyTransactions.add(company);

		assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcClient, "company"));
		assertEquals(1, JdbcTestUtils.countRowsInTable(jdbcClient, "address"));
	}
}
