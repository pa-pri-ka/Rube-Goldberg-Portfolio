package be.pa_pri_ka.Rube_Goldberg_Portofolio.jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
public class Company {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@NotNull
	private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "address_id")
	private Address address;

	@Length(max = 64)
	@NotNull
	private String name;

	public Long getId() {
		return id;
	}

	public Address getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}

	public void setId(@NotNull final Long id) {
		this.id = id;
	}

	public void setAddress(final Address address) {
		this.address = address;
	}

	public void setName(@Length(max = 64) @NotNull final String name) {
		this.name = name;
	}
}
