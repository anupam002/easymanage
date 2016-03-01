package com.companies.easymanage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.companies.easymanage.exception.CompanyExceptionHandler;
import com.companies.easymanage.model.BeneficialOwnerModel;
import com.companies.easymanage.model.CompanyModel;
import com.companies.easymanage.service.CompanyDetailService;
import com.companies.easymanage.validator.CompanyValidator;

/**
 * Functionality methods are exposed form controller 
 * @author anupamsrivastava
 *
 */
@Controller
public class CompanyDetailController{
	
	@Autowired
	private CompanyDetailService companyDetailService;
	
	@Autowired
	private CompanyValidator companyValidator;
	/**
	 * Function is responsible to create new company
	 * @return list of companies
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyDetailController.class);
	
	
	@RequestMapping(value="/createCompany", method=RequestMethod.POST)
	public ResponseEntity<CompanyModel> createCompany(@RequestBody CompanyModel companyModel, BindingResult result){
		try{
			if(null != companyModel){
				logger.info("Creating new company with Id {} and company name {}",companyModel.getCompanyId(), companyModel.getCompanyName());
				if(companyDetailService.findByCompanyId(companyModel.getCompanyId())!=null || 
						companyDetailService.findByCompanyName(companyModel.getCompanyName())!=null ){
					return new ResponseEntity<CompanyModel>(HttpStatus.CONFLICT);
				}
				companyValidator.validate(companyModel, result);
				if(result.hasErrors()){
					logger.info("Please check all mandatory fileds for company Id {} and company name {}",companyModel.getCompanyId(), companyModel.getCompanyName());
					return new ResponseEntity<CompanyModel>(HttpStatus.NOT_ACCEPTABLE);
				}
				companyDetailService.createCompany(companyModel);
			}
		}catch(CompanyExceptionHandler e){
			logger.error("Exception occured while creating new Company", e.getCause());
			return new ResponseEntity<CompanyModel>(HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<CompanyModel>(companyModel, HttpStatus.OK);
	}
	
	/**
	 * Function is responsible to return list of all companies
	 * @return list of companies
	 */
	@RequestMapping(value = "/getAllCompanies", method = RequestMethod.GET)
	public ResponseEntity<List<CompanyModel>> listAllCompanies() {
		List<CompanyModel> companies = null;
		try{
			companies = companyDetailService.findAllCompanies();
			if (companies.isEmpty()) {
				logger.info("No any company is registered yet");
				return new ResponseEntity<List<CompanyModel>>(HttpStatus.NO_CONTENT);
			}
		}catch(CompanyExceptionHandler e){
			logger.error("Exception occured while fetching company details please check logs for detail --> {}", e.getCause());
			return new ResponseEntity<List<CompanyModel>>(HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<List<CompanyModel>>(companies, HttpStatus.OK);
	}
	/**
	 * Function is responsible to return company By company Id
	 * @return list of companies
	 */
	
	@RequestMapping(value="/getCompanyById/{companyId}", method=RequestMethod.GET)
	public ResponseEntity<CompanyModel> getCompanyById(@PathVariable("companyId") long companyId){
		CompanyModel companyModel = null;
		try{
			companyModel = companyDetailService.findByCompanyId(companyId);
			if(null == companyModel){
				logger.info("No company registered with Id {}", companyId);
				return new ResponseEntity<CompanyModel>(HttpStatus.NOT_FOUND);
			}
		}catch(CompanyExceptionHandler e){
			logger.error("Exception occured while fetching company details for company Id {} please check logs for detail --> {}", companyId, e.getCause());
			return new ResponseEntity<CompanyModel>(HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<CompanyModel>(companyModel, HttpStatus.OK);
	}
	/**
	 * Function is responsible to modify an existing company
	 * @return updated company data
	 */
	@RequestMapping(value="/updateCompany/{companyId}", method=RequestMethod.POST)
	public ResponseEntity<CompanyModel> updateCompany(@PathVariable("companyId") long companyId, 
			@RequestBody CompanyModel companyModel){
		try{
			logger.info("Updating company details for company {}", companyModel.getCompanyId());
			CompanyModel existingCompany = companyDetailService.findByCompanyId(companyId);
			if(null == existingCompany){
				logger.info("No company registered while trying to update details for company id ->{}", companyId);
				return new ResponseEntity<CompanyModel>(HttpStatus.NOT_FOUND);
			}
			existingCompany.setAddress(companyModel.getAddress());
			existingCompany.setBeneficialOwnerModel(companyModel.getBeneficialOwnerModel());
			existingCompany.setCity(companyModel.getCity());
			existingCompany.setCompanyName(companyModel.getCompanyName());
			existingCompany.setCountry(companyModel.getCountry());
			existingCompany.setEmail(companyModel.getEmail());
			existingCompany.setPhoneNumber(companyModel.getPhoneNumber());
			companyDetailService.updateCompany(existingCompany);
		}catch(CompanyExceptionHandler e){
			logger.error("Exception occured while trying to update company for company id ->{} please check for detailed logs", companyId);
			return new ResponseEntity<CompanyModel>(HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<CompanyModel>(companyModel, HttpStatus.OK);
	}
	/**
	 * Function is responsible to add or modify an beneficial owners correspond to that company
	 * @return list of companies
	 */
	@RequestMapping(value="/addBeneficialOwner/{companyId}", method=RequestMethod.POST)
	public ResponseEntity<CompanyModel> addBeneficialOwners(@PathVariable("companyId") long companyId, 
			@RequestBody List<BeneficialOwnerModel> beneficialOwners){
		CompanyModel companyModel = null;
		try{
			companyModel = companyDetailService.findByCompanyId(companyId);
			if(null == companyModel){
				logger.info("No company registered while trying to add beneficial owners for company id ->{}", companyId);
				return new ResponseEntity<CompanyModel>(HttpStatus.NOT_FOUND);
			}
			companyDetailService.addBeneficialOwnerForCompany(beneficialOwners, companyModel);
		}catch(CompanyExceptionHandler e){
			logger.error("Exception occured while trying to add beneficial owners for company id ->{} please check for detailed logs", companyId);
			return new ResponseEntity<CompanyModel>(HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<CompanyModel>(companyModel, HttpStatus.OK);
		
	}
	
}
