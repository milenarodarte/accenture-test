package com.milena.companyAndSuppliers.controller;

import com.milena.companyAndSuppliers.model.CompanySupplier;
import com.milena.companyAndSuppliers.service.CompanyService;
import com.milena.companyAndSuppliers.service.CompanySupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companiessuppliers")
public class CompanySupplierController {
    private final CompanySupplierService companySupplierService;

    public CompanySupplierController(CompanySupplierService companySupplierService){
        this.companySupplierService =companySupplierService;
    }

    @PostMapping
    public ResponseEntity<CompanySupplier> createCompanySupplier (@RequestBody final CompanySupplier companySupplierData){
        final CompanySupplier createdCompanySupplier = companySupplierService.createCompanySupplier(companySupplierData);
        return new ResponseEntity<CompanySupplier>(createdCompanySupplier, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CompanySupplier>> readCompaniesSuppliers(){
        final List<CompanySupplier> allCompaniesSuppliers = companySupplierService.readCompanySupplier();
        return new ResponseEntity<List<CompanySupplier>>(allCompaniesSuppliers, HttpStatus.OK);
    }
}
