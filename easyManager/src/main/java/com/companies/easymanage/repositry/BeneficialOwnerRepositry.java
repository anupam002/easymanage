package com.companies.easymanage.repositry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.companies.easymanage.model.BeneficialOwnerModel;

@Repository
public interface BeneficialOwnerRepositry extends CrudRepository<BeneficialOwnerModel, Long>{
	
}
