package be.pa_pri_ka.Rube_Goldberg_Portofolio.jpa;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class CompanyTransactions {

	private final CompanyJpaOnlyRepository companyJpaOnlyRepository;

	CompanyTransactions(CompanyJpaOnlyRepository companyJpaOnlyRepository) {
		this.companyJpaOnlyRepository = companyJpaOnlyRepository;
	}

	// ENTITY MANAGER

	@Transactional
	public Long addCompanyUsingEm(Company company) {
		return companyJpaOnlyRepository.addUsingEm(company);
	}

	@Transactional
	public Company findCompanyByIdUsingEm(Long id) {
		return companyJpaOnlyRepository.findByIdUsingEm(id);
	}

	@Transactional
	public Company updateCompanyUsingEm(Company company) {
		return companyJpaOnlyRepository.updateCompanyUsingEm(company);
	}

	@Transactional
	public void deleteCompanyUsingEm(Company company) {
		companyJpaOnlyRepository.deleteCompanyUsingEm(company);
	}

	// CRITERIA
	@Transactional
	public Company findCompanyByNameUsingCriteria(String name) {
		return companyJpaOnlyRepository.findByNameUsingCriteria(name);
	}

	// JPQL

	@Transactional
	public void addCompanyUsingJpql(Company company) {
//		companyJpaOnlyRepository.addUsingEm(company);
	}

	@Transactional
	public void findCompanyByNameUsingJpql(String companyName) {
	}

	@Transactional
	public void updateCompanyUsingJpql(Company company) {
	}

	@Transactional
	public void deleteCompanyUsingJpql(Company company) {
	}
}
