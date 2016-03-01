package com.companies.easymanage;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.companies.easymanage.exception.CompanyExceptionHandler;
import com.companies.easymanage.model.BeneficialOwnerModel;
import com.companies.easymanage.model.CompanyModel;
import com.companies.easymanage.repositry.CompanyDetailsRepositry;
import com.companies.easymanage.service.CompanyDetailService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CompanyEntryPoint.class)
@WebAppConfiguration
public class EasyManageAppTest {
	
	@Autowired
	CompanyDetailService companyDetailService;
	
	@Autowired
	CompanyDetailsRepositry companyDetailsRepositry;
	
	@Before
	public void setup(){
		CompanyModel companyModel = new CompanyModel();
		companyModel.setCompanyId(002);
		companyModel.setCompanyName("TestCompany");
		companyModel.setCountry("India");
		companyModel.setEmail("anupam@gmail.com");
		companyModel.setPhoneNumber("98277388847");
		companyModel.setAddress("C.P");
		companyModel.setCity("New Delhi");
		List<BeneficialOwnerModel> listOfBeneficialOwnerModel = new ArrayList<BeneficialOwnerModel>(1);
		BeneficialOwnerModel beneficialOwnerModel = new BeneficialOwnerModel();
		beneficialOwnerModel.setBeneficialOwnerId(1);
		beneficialOwnerModel.setBeneficialOwnerName("Andrew");
		beneficialOwnerModel.setCompanyModel(companyModel);
		listOfBeneficialOwnerModel.add(beneficialOwnerModel);
		companyModel.setBeneficialOwnerModel(listOfBeneficialOwnerModel);
		companyDetailsRepositry.deleteAll();
		Assert.assertEquals(0, companyDetailsRepositry.count());
		companyDetailsRepositry.save(companyModel);
		
	}
	/**
	 * Test function to get all list of companies
	 */
	@Test
	public void testListOfCompaines(){
		try {
			List<CompanyModel> listofCompanies = companyDetailService.findAllCompanies();
			Assert.assertEquals(1, listofCompanies.size());
			for (CompanyModel companyModel : listofCompanies) {
				Assert.assertEquals("TestCompany", companyModel.getCompanyName());
				Assert.assertEquals(1, companyModel.getBeneficialOwnerModel().size());
				Assert.assertNotNull(companyModel.getBeneficialOwnerModel().get(0).getBeneficialOwnerId());
				Assert.assertEquals("Andrew", companyModel.getBeneficialOwnerModel().get(0).getBeneficialOwnerName());
			}
		} catch (CompanyExceptionHandler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Test function for creating new company
	 * Required params - Company Model with Required params
	 * Expected Output - Company should be create into Db
	 */
	@Test
	public void testCreateCompany() {
		CompanyModel companyModel = new CompanyModel();
		companyModel.setCompanyId(003);
		companyModel.setCompanyName("FaceBook");
		companyModel.setCountry("India");
		companyModel.setEmail("anupam@gmail.com");
		companyModel.setPhoneNumber("98277388847");
		companyModel.setAddress("C.P");
		companyModel.setCity("New Delhi");
		List<BeneficialOwnerModel> listOfBeneficialOwnerModel = new ArrayList<BeneficialOwnerModel>(1);
		BeneficialOwnerModel beneficialOwnerModel = new BeneficialOwnerModel();
		beneficialOwnerModel.setBeneficialOwnerId(1);
		beneficialOwnerModel.setBeneficialOwnerName("Andrew Smith");
		beneficialOwnerModel.setCompanyModel(companyModel);
		listOfBeneficialOwnerModel.add(beneficialOwnerModel);
		companyModel.setBeneficialOwnerModel(listOfBeneficialOwnerModel);
		try {
			 companyDetailService.createCompany(companyModel);
			 CompanyModel newCompany = companyDetailService.findByCompanyName("FaceBook");
			 Assert.assertNotNull(newCompany);
			 Assert.assertEquals("FaceBook", newCompany.getCompanyName());
			 Assert.assertEquals(1, newCompany.getBeneficialOwnerModel().size());
			 Assert.assertEquals("Andrew Smith", newCompany.getBeneficialOwnerModel().get(0).getBeneficialOwnerName());
		} catch (CompanyExceptionHandler e) {
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * Test function for creating new company
	 * Required params - Company Model with Required params
	 * Expected Output - Company should be create into Db
	 */
	@Test
	public void testUpdateCompany() {
		try {System.out.println(companyDetailService.findAllCompanies().get(0).getCompanyName());
			 CompanyModel newCompany = companyDetailService.findByCompanyName("TestCompany");
			 newCompany.setCompanyName("Facebook updated");
			 companyDetailService.updateCompany(newCompany);
			 newCompany =  companyDetailService.findByCompanyName("Facebook updated");
			 Assert.assertNotNull(newCompany);
			 Assert.assertEquals("Facebook updated", newCompany.getCompanyName());
			 Assert.assertEquals(1, newCompany.getBeneficialOwnerModel().size());
			 Assert.assertEquals("Andrew", newCompany.getBeneficialOwnerModel().get(0).getBeneficialOwnerName());
		} catch (CompanyExceptionHandler e) {
			Assert.fail(e.getMessage());
		}
	}
	
}
