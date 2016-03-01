package com.companies.easymanage.service;

import java.util.List;

import com.companies.easymanage.exception.CompanyExceptionHandler;
import com.companies.easymanage.model.BeneficialOwnerModel;
import com.companies.easymanage.model.CompanyModel;

public interface CompanyDetailService {
	
	void createCompany(CompanyModel companyModel) throws CompanyExceptionHandler;
	
	List<CompanyModel> findAllCompanies() throws CompanyExceptionHandler;
	
	CompanyModel findByCompanyId(long companyId) throws CompanyExceptionHandler;
	
	CompanyModel findByCompanyName(String companyName) throws CompanyExceptionHandler;
	
	void updateCompany(CompanyModel companyModel) throws CompanyExceptionHandler;
	
	void addBeneficialOwnerForCompany(
			List<BeneficialOwnerModel> beneficalOwners,
			CompanyModel companyModel)throws CompanyExceptionHandler;
}
