package com.greedz.posApp.business.abstracts;

import com.greedz.posApp.business.requests.productCategoryRequests.CreateProductCategoryRequest;
import com.greedz.posApp.business.requests.productCategoryRequests.DeleteProductCategoryRequest;
import com.greedz.posApp.business.requests.productCategoryRequests.UpdateProductCategoryRequest;
import com.greedz.posApp.business.responses.productCategoryResponse.ListProductCategoryDto;
import com.greedz.posApp.core.utilities.results.DataResult;
import com.greedz.posApp.core.utilities.results.Result;

import java.util.List;

public interface ProductCategoryService {

    DataResult<List<ListProductCategoryDto>> getAll();

    Result add(CreateProductCategoryRequest createProductCategoryRequest);
    Result update(UpdateProductCategoryRequest updateProductCategoryRequest);
    Result delete(DeleteProductCategoryRequest deleteProductCategoryRequest);
}
