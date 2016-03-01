package com.companies.easymanage.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.companies.easymanage.exception.CompanyExceptionHandler;
import com.companies.easymanage.model.BeneficialOwnerModel;
import com.companies.easymanage.model.CompanyModel;
import com.companies.easymanage.repositry.BeneficialOwnerRepositry;
import com.companies.easymanage.repositry.CompanyDetailsRepositry;
import com.companies.easymanage.service.CompanyDetailService;

@Service
@Transactional
public class CompanyDetailServiceImpl implements CompanyDetailService{
	
	@Autowired
	private CompanyDetailsRepositry companyDetailsRepositry;

	@Autowired
	private BeneficialOwnerRepositry beneficialOwnerRepositry;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void createCompany(CompanyModel companyModel)throws CompanyExceptionHandler {
		companyDetailsRepositry.save(companyModel);
	}
	
	public List<CompanyModel> findAllCompanies()throws CompanyExceptionHandler {
		Iterable<CompanyModel> companyDetails = companyDetailsRepositry.findAll();
		List<CompanyModel> allCompanyDetails = new ArrayList<CompanyModel>();
		for (CompanyModel companyModel : companyDetails) {
			allCompanyDetails.add(companyModel);
		}
		return allCompanyDetails;
	}

	public CompanyModel findByCompanyId(long companyId)throws CompanyExceptionHandler {
		return companyDetailsRepositry.findOne(companyId);
	}

	public CompanyModel findByCompanyName(String companyName)throws CompanyExceptionHandler {
		return companyDetailsRepositry.findByCompanyName(companyName);
	}

	public void updateCompany(CompanyModel companyModel)throws CompanyExceptionHandler {
		entityManager.merge(companyModel);
	}

	public void addBeneficialOwnerForCompany(
			List<BeneficialOwnerModel> beneficalOwners,
			CompanyModel companyModel)throws CompanyExceptionHandler {
		
		for (BeneficialOwnerModel beneficialOwnerModel : beneficalOwners) {
			beneficialOwnerModel.setCompanyModel(companyModel);
			BeneficialOwnerModel beneficialOwner = beneficialOwnerRepositry.findOne(beneficialOwnerModel.getBeneficialOwnerId());
			if(beneficialOwner == null){
				beneficialOwner = new BeneficialOwnerModel();
				beneficialOwner.setBeneficialOwnerId(beneficialOwnerModel.getBeneficialOwnerId());
				beneficialOwner.setBeneficialOwnerName(beneficialOwnerModel.getBeneficialOwnerName());
				beneficialOwner.setCompanyModel(companyModel);
			}else{
				beneficialOwner.setBeneficialOwnerName(beneficialOwnerModel.getBeneficialOwnerName());
			}
			entityManager.merge(beneficialOwner);
		}
	}
	
}
