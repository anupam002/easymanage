#Easy Manage Application
This project is basically divided into two parts:

Wasy Manage application developed using spring boot, H2 In memory database and Embedded tomcat server.
Application usually launch on 8090 port but user can change port as specific through application.properties file.

# Functionality covered

* Create new company
* Get a list of all companies
* Get details about a company
* Able to update a company
* Able to add beneficial owner(s) of the company

A Company has the following attributes:

* Company ID
* Name
* Address
* City
* Country
* E­mail (not required)
* Phone Number (not required)
* One or more beneficial owner(s)

The service contains following rest methods:

| Rest Method         | HTTPVerb | Path Variable  |
|---------------------|----------|----------------|
| createCompany       | GET      |None            |
| getAllCompanies     | GET      |None            |
| getCompanyById      | POST     |companyId       |
| updateCompany       | POST     |companyId       |
| addBeneficialOwner  | POST     |companyId       |


Testing webservice with cURL:
-----------------------------

* *New company*:

    curl -v -H "Content-Type:application/json" -X POST http://localhost:8090/createCompany -d "{\"companyName\":\"Anupam Softwares\",\"address\":\"Bad Godesbers\",\"city\":\"Colonge\",\"country\":\"Germany\",\"email\":\"anupam@gmail.com\",\"phone\":\"9811728738\"}"

* *List of all companies*:

    curl -X GET http://localhost:8090/getAllCompanies
	
* * Get company by Id

	curl -X GET http://localhost:8090/getCompanyById/1

* *Updating a company*:

    curl -v -H "Content-Type:application/json" -X PUT http://localhost:8090/updateCompany/1 -d "{\"companyName\":\"Anupam AG\",\"address\":\"Frankfurt\",\"city\":\"Frankfurt\",\"country\":\"Germany\",\"email\":\"anupam@frankfurt.com\",\"phone\":\"3272673678\"}"

* *Adding Beneficial Owner to company*:

    curl -v -H "Content-Type:application/json" -X PUT http://localhost:8090/addBeneficialOwner/1 -d "[{\"beneficialOwnerName\":\"Albert\"},{\"beneficialOwnerName\":\"William richards\"}]"

Considerations
--------------
 * * For better security we can use token using spring security and we can pass those token with each request header. Accordingly we can retrieve tokens and validate for the same.



