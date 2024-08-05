package com.greedz.posApp.api.controllers;

import com.greedz.posApp.business.abstracts.ProductCategoryService;
import com.greedz.posApp.business.requests.customerRequests.CreateCustomerRequest;
import com.greedz.posApp.business.requests.customerRequests.DeleteCustomerRequest;
import com.greedz.posApp.business.requests.customerRequests.UpdateCustomerRequest;
import com.greedz.posApp.business.requests.productCategoryRequests.CreateProductCategoryRequest;
import com.greedz.posApp.business.requests.productCategoryRequests.DeleteProductCategoryRequest;
import com.greedz.posApp.business.requests.productCategoryRequests.UpdateProductCategoryRequest;
import com.greedz.posApp.business.responses.customerResponse.ListCustomerDto;
import com.greedz.posApp.business.responses.productCategoryResponse.ListProductCategoryDto;
import com.greedz.posApp.core.utilities.results.DataResult;
import com.greedz.posApp.core.utilities.results.Result;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productcategories")
public class ProductCategoryController {

    private ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateProductCategoryRequest createProductCategoryRequest){
        return this.productCategoryService.add(createProductCategoryRequest);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid UpdateProductCategoryRequest updateProductCategoryRequest){
        return this.productCategoryService.update(updateProductCategoryRequest);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteProductCategoryRequest deleteProductCategoryRequest){
        return this.productCategoryService.delete(deleteProductCategoryRequest);
    }

    @GetMapping("/getall")
    public DataResult<List<ListProductCategoryDto>> getAll(){
        return this.productCategoryService.getAll();
    }

}
