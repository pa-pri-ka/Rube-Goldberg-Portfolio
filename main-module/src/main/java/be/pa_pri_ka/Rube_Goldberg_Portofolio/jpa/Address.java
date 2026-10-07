package be.pa_pri_ka.Rube_Goldberg_Portofolio.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/** @noinspection unused*/
@Entity
public class Address {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@NotNull
	private Long id;

	@Length(max = 128)
	@NotNull
	private String city;

	@Length(max = 128)
	@NotNull
	private String country;

	@NotNull
	private Short number;

	@Length(max = 3)
	private String postbox;

	@Length(max = 128)
	@NotNull
	private String street;

	@Length(max = 16)
	@NotNull
	private String zipcode;

	public Long getId() {
		return id;
	}

	public void setId(@NotNull final Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(@Length(max = 128) @NotNull final String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(@Length(max = 128) @NotNull final String country) {
		this.country = country;
	}

	public Short getNumber() {
		return number;
	}

	public void setNumber(@NotNull final Short number) {
		this.number = number;
	}

	public String getPostbox() {
		return postbox;
	}

	public void setPostbox(@Length(max = 3) final String postbox) {
		this.postbox = postbox;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(@Length(max = 128) @NotNull final String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(@Length(max = 16) @NotNull final String zipcode) {
		this.zipcode = zipcode;
	}
}
