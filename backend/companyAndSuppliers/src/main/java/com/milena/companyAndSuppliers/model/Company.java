package com.milena.companyAndSuppliers.model;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length =100, nullable = false)
    private String business_name;
    @Column(length = 14, nullable = false, unique=true)
    private String cnpj;
    @Column(length = 8, nullable = false)
    private String cep;

    @ManyToMany
    @JoinTable(
            name = "company_supplier",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id")
    )
    private Set<Supplier> suppliers = new HashSet<>();

    public Company() {
    }

    public Company(String business_name, String cnpj, String cep) {
        this.business_name = business_name;
        this.cnpj = cnpj;
        this.cep = cep;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String CNPJ) {
        this.cnpj = CNPJ;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String CEP) {
        this.cep = CEP;
    }


}
