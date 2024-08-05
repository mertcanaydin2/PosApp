package com.greedz.posApp.business.responses.productCategoryResponse;

import com.greedz.posApp.entities.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class productCategoryDto {
    private Long id;
    private String categoryName;
    private Date createDate;
}
