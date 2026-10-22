package be.pa_pri_ka.Rube_Goldberg_Portofolio.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @noinspection ImplicitNumericConversion
 */
@SpringBootTest
class CompanyTransactionsITest {

	private static final String ADDRESS_DB_TABLE = "address";
	private static final String COMPANY_DB_TABLE = "company";
	private static final String ID_FIELD = "id";

	private static final String CITY = "Brussels";
	private static final String COMPANY_NAME = "Company Name";
	private static final String COMPANY_NEW_NAME = "Company New Name";
	private static final String COUNTRY = "Belgium";
	private static final short NUMBER = 123;
	private static final String POSTBOX = "0012";
	private static final String STREET = "Rue de JeSaisPas";
	private static final String STREET_NEW = "Rue de JeSaisToujoursPas";
	private static final String ZIPCODE = "1000";

	@Autowired
	private CompanyTransactions companyTransactions;
	@Autowired
	private JdbcClient jdbcClient;

	@BeforeEach
	void setUp() {
		JdbcTestUtils.deleteFromTables(jdbcClient, "employee", "recruiter", COMPANY_DB_TABLE, ADDRESS_DB_TABLE);
	}

	@Nested
	class WhenUsingEntityManager {

		@Test
		void addsACompany() {
			companyTransactions.addCompanyUsingEm(buildCompany());

			int rowsCount = JdbcTestUtils.countRowsInTable(jdbcClient, COMPANY_DB_TABLE);
			assertThat(rowsCount).isEqualTo(1);
		}

		@Test
		void addsACompanyAndAddress() {
			Company company = buildCompany();
			company.setAddress(buildAddress());

			companyTransactions.addCompanyUsingEm(company);

			assertThat(JdbcTestUtils.countRowsInTable(jdbcClient, COMPANY_DB_TABLE)).isEqualTo(1);
			assertThat(JdbcTestUtils.countRowsInTable(jdbcClient, ADDRESS_DB_TABLE)).isEqualTo(1);
		}

		@Test
		void findsACompanyAndAddressById() {
			Company company = buildCompany();
			company.setAddress(buildAddress());
			Long id = companyTransactions.addCompanyUsingEm(company);

			Company companyFound = companyTransactions.findCompanyByIdUsingEm(id);

			assertThat(companyFound).isNotNull();
			assertThat(companyFound).usingRecursiveComparison().ignoringFields(ID_FIELD).isEqualTo(company);
		}

		@Test
		void updatesACompanyAndAddressUsingEm() {
			Company company = buildCompany();
			company.setAddress(buildAddress());
			Long id = companyTransactions.addCompanyUsingEm(company);

			company.setName(COMPANY_NEW_NAME);
			company.getAddress().setPostbox(POSTBOX);
			company.getAddress().setStreet(STREET_NEW);
			Company updated = companyTransactions.updateCompanyUsingEm(company);

			assertThat(updated).isNotNull();
			assertThat(JdbcTestUtils.countRowsInTable(jdbcClient, COMPANY_DB_TABLE)).isEqualTo(1);
			assertThat(JdbcTestUtils.countRowsInTable(jdbcClient, ADDRESS_DB_TABLE)).isEqualTo(1);
			Company retrieved = companyTransactions.findCompanyByIdUsingEm(id);
			assertThat(retrieved).usingRecursiveComparison().ignoringFields().isEqualTo(company);
		}

		@Test
		void deletesACompanyUsingEm() {
			Company company = buildCompany();
			company.setAddress(buildAddress());
			companyTransactions.addCompanyUsingEm(company);
			assertThat(JdbcTestUtils.countRowsInTable(jdbcClient, COMPANY_DB_TABLE)).isEqualTo(1);
			assertThat(JdbcTestUtils.countRowsInTable(jdbcClient, ADDRESS_DB_TABLE)).isEqualTo(1);

			companyTransactions.deleteCompanyUsingEm(company); // Company also has "id" field updated after persist

			assertThat(JdbcTestUtils.countRowsInTable(jdbcClient, COMPANY_DB_TABLE)).isEqualTo(0);
			assertThat(JdbcTestUtils.countRowsInTable(jdbcClient, ADDRESS_DB_TABLE)).isEqualTo(0);
		}
	}

	@Nested
	class WhenUsingCriteria {

		@Test
		void findCompanyByNameUsingCriteria() {
			Company company = buildCompany();
			company.setAddress(buildAddress());
			companyTransactions.addCompanyUsingEm(company);

			Company companyFound = companyTransactions.findCompanyByNameUsingCriteria(COMPANY_NAME);

			assertThat(companyFound).isNotNull();
			// TODO continue here...
		}
	}

	private static Address buildAddress() {
		Address address = new Address();
		address.setCity(CITY);
		address.setCountry(COUNTRY);
		address.setNumber(NUMBER);
		address.setStreet(STREET);
		address.setZipcode(ZIPCODE);
		return address;
	}

	private static Company buildCompany() {
		Company company = new Company();
		company.setName(COMPANY_NAME);
		return company;
	}
}
