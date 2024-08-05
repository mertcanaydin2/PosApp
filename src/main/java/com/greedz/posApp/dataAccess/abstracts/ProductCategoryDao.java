package com.greedz.posApp.dataAccess.abstracts;

import com.greedz.posApp.entities.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryDao extends JpaRepository<ProductCategoryEntity, Integer> {

    boolean existsByCategoryNameIgnoreCase(String categoryName);
}
