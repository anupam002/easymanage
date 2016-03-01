package com.companies.easymanage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="BENEFICIALOWNER")
public class BeneficialOwnerModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="OWNERID")
	private long beneficialOwnerId;
	
	@Column(name="OWNERNAME", nullable=false)
	private String beneficialOwnerName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "companyId")
	@JsonBackReference
	private CompanyModel companyModel;

	public long getBeneficialOwnerId() {
		return beneficialOwnerId;
	}

	public void setBeneficialOwnerId(long beneficialOwnerId) {
		this.beneficialOwnerId = beneficialOwnerId;
	}

	public String getBeneficialOwnerName() {
		return beneficialOwnerName;
	}

	public void setBeneficialOwnerName(String beneficialOwnerName) {
		this.beneficialOwnerName = beneficialOwnerName;
	}

	public CompanyModel getCompanyModel() {
		return companyModel;
	}

	public void setCompanyModel(CompanyModel companyModel) {
		this.companyModel = companyModel;
	}

	
	
	
}
