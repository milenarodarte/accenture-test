package com.milena.companyAndSuppliers.controller;

import com.milena.companyAndSuppliers.model.Company;
import com.milena.companyAndSuppliers.model.Supplier;
import com.milena.companyAndSuppliers.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody final Company companyData) throws Exception {
        final Company createdCompany = companyService.createCompany(companyData);

        return new ResponseEntity<Company>(createdCompany, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Company>> readCompanies(){
        final List<Company> allCompanies = companyService.readCompanies();

        return new ResponseEntity<List<Company>>(allCompanies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> retrieveCompanyById(@PathVariable final String id) throws Exception {
        final Company company = companyService.retrieveCompanyById(Long.parseLong(id));
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<Company> retrieveCompanyByCnpj(@PathVariable final String cnpj) throws Exception {
        final Company company = companyService.retrieveCompanyByCnpj(cnpj);
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }

    @GetMapping("/business_name/{businessName}")
    public ResponseEntity<List<Company>> retrieveCompanyByBusinessNAme(@PathVariable final String businessName) throws Exception {
        final List<Company> companies = companyService.retrieveCompanyByBusinessName(businessName);
        return new ResponseEntity<List<Company>>(companies, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@RequestBody final Company companyData, @PathVariable final  String id) throws Exception{
        final Company updatedCompany = companyService.updateCompany(companyData ,Long.parseLong(id));
        return new ResponseEntity<Company>(updatedCompany, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable final String id) throws Exception {
        companyService.deleteCompany(Long.parseLong(id));
        return new ResponseEntity<Void>( HttpStatus.NO_CONTENT);
    }




}
