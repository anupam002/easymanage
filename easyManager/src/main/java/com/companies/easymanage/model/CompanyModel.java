package com.companies.easymanage.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="COMPANY")
public class CompanyModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="COMPANYID")
	private long companyId;
	
	@Column(name="COMPANYNAME", nullable=false)
	private String companyName;
	
	@Column(name="ADDRESS", nullable=false)
	private String address;
	
	@Column(name="CITY", nullable=false)
	private String city;
	
	@Column(name="COUNTRY", nullable=false)
	private String country;
	
	@Column(name="EMAIL", nullable=true)
	private String email;
	
	@Column(name="PHONE", nullable=true)
	private String phoneNumber;
	
	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="companyModel")
	@JsonManagedReference
	private List<BeneficialOwnerModel> beneficialOwnerModel;

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<BeneficialOwnerModel> getBeneficialOwnerModel() {
		return beneficialOwnerModel;
	}

	public void setBeneficialOwnerModel(
			List<BeneficialOwnerModel> beneficialOwnerModel) {
		this.beneficialOwnerModel = beneficialOwnerModel;
	}
	
}
