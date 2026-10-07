package be.pa_pri_ka.Rube_Goldberg_Portofolio.jpa;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
class CompanyDAO {

	private final EntityManager entityManager;

	CompanyDAO(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	void add(Company company) {
		entityManager.persist(company);
	}
}
