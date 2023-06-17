package com.milena.companyAndSuppliers.repository;

import com.milena.companyAndSuppliers.model.CompanySupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanySupplierRepository extends JpaRepository<CompanySupplier, Long> {


}