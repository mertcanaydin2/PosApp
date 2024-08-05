package com.greedz.posApp.business.abstracts;

import com.greedz.posApp.business.requests.productRequests.CreateProductRequest;
import com.greedz.posApp.business.requests.productRequests.DeleteProductRequest;
import com.greedz.posApp.business.requests.productRequests.UpdateProductRequest;
import com.greedz.posApp.business.responses.customerResponse.ListCustomerDto;
import com.greedz.posApp.business.responses.productResponses.ListProductDto;
import com.greedz.posApp.core.utilities.results.DataResult;
import com.greedz.posApp.core.utilities.results.Result;

import java.util.List;

public interface ProductService {

    DataResult<List<ListProductDto>> getAll();
    DataResult<List<ListProductDto>> getAllPaged(int pageNo, int pageSize);

    Result add(CreateProductRequest createProductRequest);
    Result update(UpdateProductRequest updateProductRequest);
    Result delete(DeleteProductRequest deleteProductRequest);
}
