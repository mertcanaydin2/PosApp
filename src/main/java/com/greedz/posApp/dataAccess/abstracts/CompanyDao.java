package com.greedz.posApp.dataAccess.abstracts;

import com.greedz.posApp.entities.CompanyEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao extends JpaRepository<CompanyEntity, Integer> {
    boolean existsByCompanyNameIgnoreCase(String companyName);
}
