package com.greedz.posApp.business.concretes;

import com.greedz.posApp.business.abstracts.ProductService;
import com.greedz.posApp.business.constants.BusinessMessages;
import com.greedz.posApp.business.requests.productRequests.CreateProductRequest;
import com.greedz.posApp.business.requests.productRequests.DeleteProductRequest;
import com.greedz.posApp.business.requests.productRequests.UpdateProductRequest;
import com.greedz.posApp.business.responses.customerResponse.ListCustomerDto;
import com.greedz.posApp.business.responses.productCategoryResponse.ListProductCategoryDto;
import com.greedz.posApp.business.responses.productResponses.ListProductDto;
import com.greedz.posApp.core.utilities.mapping.ModelMapperService;
import com.greedz.posApp.core.utilities.results.*;
import com.greedz.posApp.dataAccess.abstracts.ProductDao;
import com.greedz.posApp.entities.CustomerEntity;
import com.greedz.posApp.entities.ProductCategoryEntity;
import com.greedz.posApp.entities.ProductEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductManager implements ProductService {

    private ProductDao productDao;
    private ModelMapperService modelMapperService;

    public ProductManager(ProductDao productDao, ModelMapperService modelMapperService) {
        this.productDao = productDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<ListProductDto>> getAll() {
        List<ProductEntity> productEntities = this.productDao.findAll();
        List<ListProductDto> response = productEntities.stream()
                .map(productEntity -> this.modelMapperService.forDto()
                        .map(productEntity, ListProductDto.class))
                .collect(Collectors.toList());
        if (Objects.nonNull(response)) return new SuccessDataResult<List<ListProductDto>>(response);
        return new ErrorDataResult<>(BusinessMessages.ProductMessages.PRODUCT_NOT_FOUND);
    }

    @Override
    public DataResult<List<ListProductDto>> getAllPaged(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        List<ProductEntity> productEntities = this.productDao.findAll(pageable).getContent();
        List<ListProductDto> response = productEntities.stream()
                .map(productEntity -> this.modelMapperService.forDto()
                        .map(productEntity, ListProductDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListProductDto>>(response);
    }

    @Override
    public Result add(CreateProductRequest createProductRequest) {

        ProductEntity productEntity = this.modelMapperService.forRequest()
                .map(createProductRequest, ProductEntity.class);

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        productEntity.setCreateDate(formattedDate);
        this.productDao.save(productEntity);
        if (Objects.nonNull(productEntity))
            return new SuccessResult(BusinessMessages.ProductMessages.PRODUCT_ADD_SUCCESS);
        return new ErrorResult(BusinessMessages.ProductMessages.PRODUCT_ADD_UNSUCCESS);
    }

    @Override
    public Result update(UpdateProductRequest updateProductRequest) {
        ProductEntity productEntity = this.modelMapperService.forRequest()
                .map(updateProductRequest, ProductEntity.class);

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        productEntity.setUpdateDate(formattedDate);

        this.productDao.save(productEntity);
        if (Objects.nonNull(productEntity))
            return new SuccessResult(BusinessMessages.ProductMessages.PRODUCT_UPDATE_SUCCESS);
        return new ErrorResult(BusinessMessages.ProductMessages.PRODUCT_UPDATE_UNSUCCESS);
    }

    @Override
    public Result delete(DeleteProductRequest deleteProductRequest) {
        this.productDao.deleteById(Math.toIntExact(deleteProductRequest.getProductId()));
        if (Objects.nonNull(deleteProductRequest.getProductId()))
            return new SuccessResult(BusinessMessages.ProductMessages.PRODUCT_DELETE_SUCCESS);
        return new ErrorResult(BusinessMessages.ProductMessages.PRODUCT_DELETE_UNSUCCESS);
    }
}
