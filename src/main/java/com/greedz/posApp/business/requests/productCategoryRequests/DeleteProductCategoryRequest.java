package com.greedz.posApp.business.requests.productCategoryRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteProductCategoryRequest {

    private Long categoryId;
}
