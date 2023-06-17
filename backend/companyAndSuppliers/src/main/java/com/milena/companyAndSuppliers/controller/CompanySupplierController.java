package com.milena.companyAndSuppliers.controller;

import com.milena.companyAndSuppliers.model.CompanySupplier;
import com.milena.companyAndSuppliers.model.Supplier;
import com.milena.companyAndSuppliers.service.CompanyService;
import com.milena.companyAndSuppliers.service.CompanySupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies_suppliers")
public class CompanySupplierController {
    private final CompanySupplierService companySupplierService;

    public CompanySupplierController(CompanySupplierService companySupplierService){
        this.companySupplierService =companySupplierService;
    }

    @PostMapping
    public ResponseEntity<CompanySupplier> createCompanySupplier (@RequestBody final CompanySupplier companySupplierData){
        System.out.println(companySupplierData);
        final CompanySupplier createdCompanySupplier = companySupplierService.createCompanySupplier(companySupplierData);
        return new ResponseEntity<CompanySupplier>(createdCompanySupplier, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CompanySupplier>> readCompaniesSuppliers(){
        final List<CompanySupplier> allCompaniesSuppliers = companySupplierService.readCompanySupplier();
        return new ResponseEntity<List<CompanySupplier>>(allCompaniesSuppliers, HttpStatus.OK);
    }
    /*@GetMapping("/companyId/{companyId}")
    public ResponseEntity<List<CompanySupplier>> retrieveSupplierCompanyId(@PathVariable final String companyId) throws Exception {
        final List<CompanySupplier> companySuppliers = companySupplierService.retrieveSupplierByCompanyId(Long.parseLong(companyId));
        return new ResponseEntity<List<CompanySupplier>>(companySuppliers, HttpStatus.OK);
    };
    @GetMapping("/suppliersId/{suppliersId}")
    public ResponseEntity<List<CompanySupplier>> retrieveCompanySupplierBySuppliersId(@PathVariable final String suppliersId) throws Exception {
        final List<CompanySupplier> companySuppliers = companySupplierService.retrieveCompanySupplierBySupplierId(Long.parseLong(suppliersId));
        return new ResponseEntity<List<CompanySupplier>>(companySuppliers, HttpStatus.OK);
    };*/

    @GetMapping("/{id}")
    public ResponseEntity<CompanySupplier> retrieveCompanySupplierById(@PathVariable final String id) throws Exception {
        final CompanySupplier companySupplier = companySupplierService.retrieveCompanySupplierById(Long.parseLong(id));
        return new ResponseEntity<CompanySupplier>(companySupplier, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompanySupplier(@PathVariable final String id) throws Exception {
        companySupplierService.deleteCompanySupplier(Long.parseLong(id));
        return new ResponseEntity<Void>( HttpStatus.NO_CONTENT);
    }
}
