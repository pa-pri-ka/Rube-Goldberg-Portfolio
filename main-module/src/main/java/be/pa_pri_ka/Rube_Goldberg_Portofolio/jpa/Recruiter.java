package be.pa_pri_ka.Rube_Goldberg_Portofolio.jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
public class Recruiter {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@NotNull
	private Long id;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@Length(max = 64)
	@NotNull
	private String firstName;

	@Length(max = 64)
	@NotNull
	private String lastName;

	public Long getId() {
		return id;
	}

	public Company getCompany() {
		return company;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setId(@NotNull final Long id) {
		this.id = id;
	}

	public void setCompany(final Company company) {
		this.company = company;
	}

	public void setFirstName(@Length(max = 64) @NotNull final String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(@Length(max = 64) @NotNull final String lastName) {
		this.lastName = lastName;
	}
}
