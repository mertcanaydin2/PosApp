package com.greedz.posApp.business.concretes;

import com.greedz.posApp.business.abstracts.ProductCategoryService;
import com.greedz.posApp.business.constants.BusinessMessages;
import com.greedz.posApp.business.requests.productCategoryRequests.CreateProductCategoryRequest;
import com.greedz.posApp.business.requests.productCategoryRequests.DeleteProductCategoryRequest;
import com.greedz.posApp.business.requests.productCategoryRequests.UpdateProductCategoryRequest;
import com.greedz.posApp.business.responses.customerResponse.ListCustomerDto;
import com.greedz.posApp.business.responses.productCategoryResponse.ListProductCategoryDto;
import com.greedz.posApp.core.utilities.mapping.ModelMapperService;
import com.greedz.posApp.core.utilities.results.*;
import com.greedz.posApp.dataAccess.abstracts.ProductCategoryDao;
import com.greedz.posApp.entities.CustomerEntity;
import com.greedz.posApp.entities.ProductCategoryEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductCategoryManager implements ProductCategoryService {

    private ProductCategoryDao productCategoryDao;
    private ModelMapperService modelMapperService;

    public ProductCategoryManager(ProductCategoryDao productCategoryDao, ModelMapperService modelMapperService) {
        this.productCategoryDao = productCategoryDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<ListProductCategoryDto>> getAll() {
        List<ProductCategoryEntity> productCategoryEntities = this.productCategoryDao.findAll();
        List<ListProductCategoryDto> response = productCategoryEntities.stream()
                .map(productCategoryEntity -> this.modelMapperService.forDto()
                        .map(productCategoryEntity, ListProductCategoryDto.class))
                .collect(Collectors.toList());
        if (Objects.nonNull(response))return new SuccessDataResult<List<ListProductCategoryDto>>(response);
        return new ErrorDataResult<>(BusinessMessages.ProductCategoryMessages.PRODUCT_CATG_NOT_FOUND);
    }

    @Override
    public Result add(CreateProductCategoryRequest createProductCategoryRequest) {
        if (checkCategoryIsExists(createProductCategoryRequest.getCategoryName())){
            return new ErrorResult(BusinessMessages.ProductCategoryMessages.PRODUCT_CATG_IS_EXISTS);
        }
        ProductCategoryEntity productCategoryEntity = this.modelMapperService.forRequest()
                .map(createProductCategoryRequest, ProductCategoryEntity.class);

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        productCategoryEntity.setCreateDate(formattedDate);

        this.productCategoryDao.save(productCategoryEntity);
        if (Objects.nonNull(productCategoryEntity))return new SuccessResult(BusinessMessages.ProductCategoryMessages.PRODUCT_CATG_ADD_SUCCESS);

        return new ErrorResult(BusinessMessages.ProductCategoryMessages.PRODUCT_CATG_ADD_UNSUCCESS);
    }

    @Override
    public Result update(UpdateProductCategoryRequest updateProductCategoryRequest) {
        ProductCategoryEntity productCategoryEntity = this.modelMapperService.forRequest()
                .map(updateProductCategoryRequest, ProductCategoryEntity.class);

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        productCategoryEntity.setUpdateDate(formattedDate);

        this.productCategoryDao.save(productCategoryEntity);
        if (Objects.nonNull(productCategoryEntity))return new SuccessResult(BusinessMessages.ProductCategoryMessages.PRODUCT_CATG_UPDATE_SUCCESS);
        return new SuccessResult(BusinessMessages.ProductCategoryMessages.PRODUCT_CATG_UPDATE_UNSUCCESS);
    }

    @Override
    public Result delete(DeleteProductCategoryRequest deleteProductCategoryRequest) {
        this.productCategoryDao.deleteById(Math.toIntExact(deleteProductCategoryRequest.getCategoryId()));
        if (Objects.nonNull(deleteProductCategoryRequest.getCategoryId()))return new SuccessResult(BusinessMessages.ProductCategoryMessages.PRODUCT_CATG_DELETE_SUCCESS);
        return new ErrorResult(BusinessMessages.ProductCategoryMessages.PRODUCT_CATG_DELETE_UNSUCCESS);

    }

    public boolean checkCategoryIsExists(String categoryName){
       if(productCategoryDao.existsByCategoryNameIgnoreCase(categoryName)) return true;
        return false;
    }
}
