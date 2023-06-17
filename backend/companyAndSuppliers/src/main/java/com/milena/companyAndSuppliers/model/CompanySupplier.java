package com.milena.companyAndSuppliers.model;

import jakarta.persistence.*;

@Entity
@Table(name="company_supplier")
public class CompanySupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;


    public Company getCompany() {
        return company;
    }
    public Long getSupplierId() {
        if (supplier != null) {
            return supplier.getId();
        }
        return null;
    }
    public Long getCompanyId() {
        if (company != null) {
            return company.getId();
        }
        return null;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public CompanySupplier(Long companyId, Long supplierId) {
    }

    public CompanySupplier(Company company, Supplier supplier, Long id, Long supplier_id) {
        this.company = company;
        this.supplier = supplier;
        this.id = id;
        this.getSupplierId = supplier_id;

    }
}
