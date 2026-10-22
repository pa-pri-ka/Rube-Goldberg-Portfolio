package be.pa_pri_ka.Rube_Goldberg_Portofolio.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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

	Company findByNameUsingCriteria(String name) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Company> query = cb.createQuery(Company.class);
		Root<Company> root = query.from(Company.class);
		CriteriaQuery<Company> companies = query.select(root).where(cb.equal(root.get("name"), name)).distinct(true);
		TypedQuery<Company> typedQuery = entityManager.createQuery(companies);
		return typedQuery.getSingleResult();
	}
}
