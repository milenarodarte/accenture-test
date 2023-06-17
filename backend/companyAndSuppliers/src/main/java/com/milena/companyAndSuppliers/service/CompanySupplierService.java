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

    public CompanySupplierService(CompanySupplierRepository companySupplierRepository, CompanyRepository companyRepository, SupplierRepository supplierRepository) {
        this.companySupplierRepository = companySupplierRepository;
        this.companyRepository = companyRepository;
        this.supplierRepository = supplierRepository;
    }
        public List<CompanySupplier> readCompanySupplier () {
            return companySupplierRepository.findAll();
        }
        public CompanySupplier retrieveCompanySupplierById (Long companySupplierId) throws Exception {
            final CompanySupplier companySupplier = companySupplierRepository.findById(companySupplierId).orElseThrow(() -> new Exception("not found by id"));
            return companySupplier;
        }
        /* public List<CompanySupplier> retrieveSupplierByCompanyId(final Long companyId) throws Exception {
            final List<CompanySupplier> companySuppliers = companySupplierRepository.findAllByCompanyId(companyId);
            if (companySuppliers.isEmpty()) {
                throw new Exception("No suppliers found by name");
            }
            return companySuppliers;
        };
        public List<CompanySupplier> retrieveCompanySupplierBySupplierId(final Long supplierId) throws Exception {
            final List<CompanySupplier> companySuppliers = companySupplierRepository.findAllBySupplierId(supplierId);
            if (companySuppliers.isEmpty()) {
                throw new Exception("No suppliers found by name");
            }
            return companySuppliers;
        }; */


         public CompanySupplier createCompanySupplier ( final CompanySupplier companySupplierData){
            // verificar antes se ja nao existe o relacionamento entre company e supplier
           Long companyId = companySupplierData.getCompanyId();
           Long supplierId = companySupplierData.getSupplierId();


            final CompanySupplier companySupplier = new CompanySupplier(companyId, supplierId);
            return companySupplierRepository.save((companySupplier));


        };

        public void deleteCompanySupplier(final long id) throws Exception{
            final CompanySupplier companySupplierToBeDeleted = companySupplierRepository.findById(id).orElseThrow(() -> new Exception("Supplier not found"));
            companySupplierRepository.delete(companySupplierToBeDeleted);
        }


}

