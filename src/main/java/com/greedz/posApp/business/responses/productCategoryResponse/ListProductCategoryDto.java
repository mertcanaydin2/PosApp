package com.greedz.posApp.business.responses.productCategoryResponse;

import com.greedz.posApp.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListProductCategoryDto {

    private Long id;
    private String categoryName;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
