package be.pa_pri_ka.Rube_Goldberg_Portofolio.jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
public class Employee {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@NotNull
	private Long id;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@Length(max = 64)
	@NotNull
	private String firstName;

	@Length(max = 64)
	@NotNull
	private String lastName;

	public Long getId() {
		return id;
	}

	public Address getAddress() {
		return address;
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

	public void setAddress(final Address address) {
		this.address = address;
	}

	public void setFirstName(@Length(max = 64) @NotNull final String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(@Length(max = 64) @NotNull final String lastName) {
		this.lastName = lastName;
	}
}
