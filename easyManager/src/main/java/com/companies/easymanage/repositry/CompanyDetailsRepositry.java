package com.companies.easymanage.repositry;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.companies.easymanage.model.CompanyModel;

/**
 * @author anupamsrivastava
 * Repositry for managing company related functionalities
 */
@Repository
public interface CompanyDetailsRepositry extends CrudRepository<CompanyModel, Long>{
	@Query("Select c from CompanyModel c where c.companyName = ?1")
	CompanyModel findByCompanyName(String companyName);
}
