package com.milena.companyAndSuppliers.service;

import com.milena.companyAndSuppliers.model.Company;
import com.milena.companyAndSuppliers.model.Supplier;
import com.milena.companyAndSuppliers.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private RestTemplate restTemplate = new RestTemplate();
    CEPValidator cepValidator = new CEPValidator(restTemplate);

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(final Company companyData) throws Exception {
        boolean isCepValid = cepValidator.validateCEP(companyData.getCep());
        if (isCepValid) {
        final Company company = new Company(companyData.getBusiness_name(), companyData.getCnpj(), companyData.getCep());
        return companyRepository.save(company);} else {
            throw new Exception("invalid CEP");
        }
    }
    public List<Company> readCompanies(){
        return companyRepository.findAll();
    }

    public Company retrieveCompanyById(final long id) throws Exception {
        final Company company = companyRepository.findById(id).orElseThrow(() -> new Exception("Company not found by ID"));
        return company;
    }

    public Company retrieveCompanyByCnpj(final String cnpj) throws Exception {
        final Company company = companyRepository.findByCnpj(cnpj).orElseThrow(() -> new Exception("Company not found by CNPJ"));;
        return company;
    };

    public List<Company> retrieveCompanyByBusinessName(final String businessName) throws Exception {
        final List<Company> companies = companyRepository.findAllByBusinessName(businessName);
        if (companies.isEmpty()) {
            throw new Exception("No companies found by name");
        }
        return companies;
    };


    public Company updateCompany(final Company companyData, final long id) throws Exception{
        final Company foundCompany = companyRepository.findById(id).orElseThrow(() -> new Exception("Company not found"));
        boolean isCepValid = cepValidator.validateCEP(companyData.getCep());
        if (isCepValid){
            foundCompany.setCep(companyData.getCep());
            foundCompany.setCnpj(companyData.getCnpj());
            foundCompany.setBusiness_name(companyData.getBusiness_name());
            return companyRepository.save(foundCompany);
        } else {
            throw new Exception("invalid CEP");
        }
    };
    public void deleteCompany(final long id) throws Exception{
        final Company companyToBeDeleted = companyRepository.findById(id).orElseThrow(() -> new Exception("Company not found"));
        companyRepository.delete(companyToBeDeleted);
    }

}
