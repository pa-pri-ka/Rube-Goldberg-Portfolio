package be.pa_pri_ka.Rube_Goldberg_Portofolio.jpa;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
class CompanyJpaOnlyRepository {

	private final EntityManager entityManager;

	CompanyJpaOnlyRepository(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	Long addUsingEm(Company company) {
		entityManager.persist(company);
		return company.getId();
	}

	Company findByIdUsingEm(Long id) {
		return entityManager.find(Company.class, id);
	}

	Company updateCompanyUsingEm(Company company) {
		return entityManager.merge(company);
	}

	void deleteCompanyUsingEm(Company company) {
		Company reference = entityManager.getReference(Company.class, company.getId());
		entityManager.remove(reference);
	}
}
