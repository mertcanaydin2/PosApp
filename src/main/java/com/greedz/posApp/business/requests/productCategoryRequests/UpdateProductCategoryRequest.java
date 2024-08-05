package com.greedz.posApp.business.requests.productCategoryRequests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductCategoryRequest {

    private Long categoryId;

    @Length(min = 1, max = 200)
    private String categoryName;
    private Date createDate;
}
