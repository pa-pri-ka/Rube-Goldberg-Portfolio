package be.pa_pri_ka.Rube_Goldberg_Portofolio.jpa;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyTransactions {

	private final CompanyDAO companyDAO;

	/** @noinspection ClassEscapesDefinedScope*/
	public CompanyTransactions(CompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	@Transactional
	public void add(Company company) {
		companyDAO.add(company);
	}
}
