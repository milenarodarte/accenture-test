package com.milena.companyAndSuppliers.service;
import com.milena.companyAndSuppliers.model.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.milena.companyAndSuppliers.model.Company;
import com.milena.companyAndSuppliers.model.CompanySupplier;

import com.milena.companyAndSuppliers.repository.CompanyRepository;
import com.milena.companyAndSuppliers.repository.CompanySupplierRepository;
import com.milena.companyAndSuppliers.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanySupplierService {
    private static final Logger logger = LoggerFactory.getLogger(CompanySupplierService.class);

    private final CompanySupplierRepository companySupplierRepository;
    private final CompanyRepository companyRepository;
    private final SupplierRepository supplierRepository;

    public CompanySupplierService(CompanySupplierRepository companySupplierRepository, CompanyRepository companyRepository, SupplierRepository supplierRepository){
        this.companySupplierRepository = companySupplierRepository;
        this.companyRepository = companyRepository;
        this.supplierRepository = supplierRepository;
    }

    public Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }
    public Supplier getSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId).orElse(null);
    }
    public CompanySupplier createCompanySupplier(final CompanySupplier companySupplierData){

        Long companyId = companySupplierData.getCompanyId();
        Long supplierId = companySupplierData.getSupplierId();


        final CompanySupplier companySupplier = new CompanySupplier(getCompanyById(companyId), getSupplierById(supplierId));
        return companySupplierRepository.save((companySupplier));
    }

    public List<CompanySupplier> readCompanySupplier(){
        return companySupplierRepository.findAll();
    }
}

