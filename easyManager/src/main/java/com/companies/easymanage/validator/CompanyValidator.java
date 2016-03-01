package com.companies.easymanage.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.companies.easymanage.model.CompanyModel;

@Component
public class CompanyValidator implements Validator{
	
	public boolean supports(Class<?> cls) {
		return CompanyModel.class.isAssignableFrom(cls);
	}
	
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "companyId", "error.companyId", "Company id is mandatory field");
		ValidationUtils.rejectIfEmpty(errors, "companyName", "error.companyName", "Company Name is mandatory field");
		ValidationUtils.rejectIfEmpty(errors, "address", "error.companyId", "Address is mandatory field");
		ValidationUtils.rejectIfEmpty(errors, "city", "error.city", "City is mandatory field");
		ValidationUtils.rejectIfEmpty(errors, "country", "error.country", "Country is mandatory field");
	}

}
