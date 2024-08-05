package com.greedz.posApp.api.controllers;

import com.greedz.posApp.business.abstracts.ProductService;
import com.greedz.posApp.business.requests.customerRequests.CreateCustomerRequest;
import com.greedz.posApp.business.requests.customerRequests.DeleteCustomerRequest;
import com.greedz.posApp.business.requests.customerRequests.UpdateCustomerRequest;
import com.greedz.posApp.business.requests.productRequests.CreateProductRequest;
import com.greedz.posApp.business.requests.productRequests.DeleteProductRequest;
import com.greedz.posApp.business.requests.productRequests.UpdateProductRequest;
import com.greedz.posApp.business.responses.customerResponse.ListCustomerDto;
import com.greedz.posApp.business.responses.productResponses.ListProductDto;
import com.greedz.posApp.core.utilities.results.DataResult;
import com.greedz.posApp.core.utilities.results.Result;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateProductRequest createProductRequest){
        return this.productService.add(createProductRequest);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid UpdateProductRequest updateProductRequest){
        return this.productService.update(updateProductRequest);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteProductRequest deleteProductRequest){
        return this.productService.delete(deleteProductRequest);
    }

    @GetMapping("/getall")
    public DataResult<List<ListProductDto>> getAll(){
        return this.productService.getAll();
    }

    @GetMapping("/getallpaged")
    public DataResult<List<ListProductDto>> getAllPaged(int pageNo, int pageSize){
        return this.productService.getAllPaged(pageNo, pageSize);
    }
}
