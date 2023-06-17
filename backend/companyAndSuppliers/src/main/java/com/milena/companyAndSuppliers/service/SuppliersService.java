package com.milena.companyAndSuppliers.service;

import com.milena.companyAndSuppliers.model.Supplier;
import com.milena.companyAndSuppliers.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SuppliersService {

    private final SupplierRepository supplierRepository;
    @Autowired

    private RestTemplate restTemplate = new RestTemplate();
    CEPValidator cepValidator = new CEPValidator(restTemplate);

    public SuppliersService(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    public Supplier createSupplier(final Supplier supplierData) throws Exception {
        boolean isCepValid = cepValidator.validateCEP(supplierData.getCep());
        boolean isParana = cepValidator.isParana(supplierData.getCep());

        if (isParana == true) {
            System.out.println("PARANA");
        }
        if (isCepValid) {
        if (supplierData.getCpfCnpj().length() == 11) {
            final Supplier supplier = new Supplier(supplierData.getName(),
                    supplierData.getCpfCnpj(), supplierData.getEmail(),
                    supplierData.getCep(), supplierData.getRg(),
                    supplierData.getBirthdate());
            return supplierRepository.save(supplier);
        } else if (supplierData.getCpfCnpj().length() == 14){
            final Supplier supplier = new Supplier(supplierData.getName(),
                    supplierData.getCpfCnpj(), supplierData.getEmail(),
                    supplierData.getCep(), null, null );
            return supplierRepository.save(supplier);
        } else {
            throw new Exception("your CPF must have 11 numbers or your CNPJ must have 14 numbers");
        }}
        else {
            throw new Exception("invalid CEP");
        }

    };
    public List<Supplier> readSuppliers(){
        return supplierRepository.findAll();
    }

    public Supplier retrieveSupplierById(final long id) throws Exception {
        final Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new Exception("supplier not found by id"));
        return supplier;
    }
    public List<Supplier> retrieveSupplierByName(final String name) throws Exception {
        final List<Supplier> suppliers = supplierRepository.findAllByName(name);
        if (suppliers.isEmpty()) {
            throw new Exception("No suppliers found by name");
        }
        return suppliers;
    };
    public Supplier retrieveSupplierByCpfCnpj(final String cpfCnpj) throws Exception {
        final Supplier supplier = supplierRepository.findByCpfCnpj(cpfCnpj).orElseThrow(() -> new Exception("Supplier not found by CPF/CNPJ"));;
        return supplier;
    };

    public Supplier updateSupplier(final Supplier supplierData, final long id) throws Exception{
        final Supplier foundSupplier = supplierRepository.findById(id).orElseThrow(() -> new Exception("Supplier not found"));
        boolean isCepValid = cepValidator.validateCEP(supplierData.getCep());

        if (isCepValid) {
            if (foundSupplier.getCpfCnpj().length() == 11) {
                foundSupplier.setName(supplierData.getName());
                foundSupplier.setCep(supplierData.getCep());
                foundSupplier.setBirthdate(supplierData.getBirthdate());
                foundSupplier.setEmail(supplierData.getEmail());
                foundSupplier.setCpfCnpj(supplierData.getCpfCnpj());
                foundSupplier.setRg(supplierData.getRg());
                return supplierRepository.save(foundSupplier);
            } else if (foundSupplier.getCpfCnpj().length() == 14) {
                foundSupplier.setName(supplierData.getName());
                foundSupplier.setCep(supplierData.getCep());
                foundSupplier.setEmail(supplierData.getEmail());
                foundSupplier.setCpfCnpj(supplierData.getCpfCnpj());
                return supplierRepository.save(foundSupplier);
            } else {
                throw new Exception("your CPF must have 11 numbers or your CNPJ must have 14 numbers");
            }
        } else {
                  throw new Exception("invalid CEP");
        }
        

    };

    public void deleteSupplier(final long id) throws Exception{
        final Supplier supplierToBeDeleted = supplierRepository.findById(id).orElseThrow(() -> new Exception("Supplier not found"));
        supplierRepository.delete(supplierToBeDeleted);
    }

}
