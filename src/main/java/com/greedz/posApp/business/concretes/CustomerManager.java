package com.greedz.posApp.business.concretes;

import com.greedz.posApp.business.abstracts.CustomerService;
import com.greedz.posApp.business.constants.BusinessMessages;
import com.greedz.posApp.business.requests.customerRequests.CreateCustomerRequest;
import com.greedz.posApp.business.requests.customerRequests.DeleteCustomerRequest;
import com.greedz.posApp.business.requests.customerRequests.UpdateCustomerRequest;
import com.greedz.posApp.business.responses.customerResponse.ListCustomerDto;
import com.greedz.posApp.core.utilities.mapping.ModelMapperService;
import com.greedz.posApp.core.utilities.results.*;
import com.greedz.posApp.dataAccess.abstracts.CustomerDao;
import com.greedz.posApp.entities.CustomerEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomerManager implements CustomerService {

    private CustomerDao customerDao;
    private ModelMapperService modelMapperService;

    public CustomerManager(CustomerDao customerDao, ModelMapperService modelMapperService) {
        this.customerDao = customerDao;
        this.modelMapperService = modelMapperService;
    }



    @Override
    public DataResult<List<ListCustomerDto>> getAll() {

        List<CustomerEntity> customerEntities = this.customerDao.findAll();
        List<ListCustomerDto> response = customerEntities.stream()
                .map(customerEntity -> this.modelMapperService.forDto()
                        .map(customerEntity, ListCustomerDto.class))
                .collect(Collectors.toList());
        if (Objects.nonNull(response))return new SuccessDataResult<List<ListCustomerDto>>(response);
        return new ErrorDataResult<>(BusinessMessages.CustomerMessages.CUSTOMER_NOT_FOUND);
    }

    @Override
    public DataResult<List<ListCustomerDto>> getAllPaged(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo -1, pageSize);

        List<CustomerEntity> customerEntities = this.customerDao.findAll(pageable).getContent();
        List<ListCustomerDto> response = customerEntities.stream()
                .map(customerEntity -> this.modelMapperService.forDto()
                        .map(customerEntity,ListCustomerDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListCustomerDto>>(response);
    }

    @Override
    public DataResult<List<ListCustomerDto>> getAllByFirstName(String firstName) {
        List<CustomerEntity> result = this.customerDao.getAllByFirstName(firstName);
        List<ListCustomerDto> response = result.stream()
                .map(customerEntity -> this.modelMapperService.forDto()
                        .map(customerEntity,ListCustomerDto.class))
                .collect(Collectors.toList());
        if (Objects.nonNull(response))return new SuccessDataResult<List<ListCustomerDto>>(response);
        return new ErrorDataResult<>(BusinessMessages.CustomerMessages.CUSTOMER_NOT_FOUND);

    }

    @Override
    public DataResult<List<ListCustomerDto>> getAllById(Long customerId) {
        List<CustomerEntity> result = this.customerDao.getAllById(customerId);
        List<ListCustomerDto> response = result.stream()
                .map(customerEntity -> this.modelMapperService.forDto()
                        .map(customerEntity,ListCustomerDto.class))
                .collect(Collectors.toList());
        if (Objects.nonNull(response))return new SuccessDataResult<List<ListCustomerDto>>(response);
        return new ErrorDataResult<>(BusinessMessages.CustomerMessages.CUSTOMER_NOT_FOUND);

    }

    @Override
    public Result add(CreateCustomerRequest createCustomerRequest) {
        if(checkCustomerIsExists(createCustomerRequest.getNationalId()))return new ErrorResult(BusinessMessages.CustomerMessages.CUSTOMER_IS_EXISTS);

        CustomerEntity customerEntity = this.modelMapperService.forRequest()
                .map(createCustomerRequest, CustomerEntity.class);

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        customerEntity.setCreateDate(formattedDate);
        this.customerDao.save(customerEntity);

        if (Objects.nonNull(customerEntity))return new SuccessResult(BusinessMessages.CustomerMessages.CUSTOMER_ADD_SUCCESS);
        return new ErrorResult(BusinessMessages.CustomerMessages.CUSTOMER_ADD_UNSUCCESS);
    }

    @Override
    public Result update(UpdateCustomerRequest updateCustomerRequest) {

        CustomerEntity customerEntity = this.modelMapperService.forRequest()
                .map(updateCustomerRequest, CustomerEntity.class);

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        customerEntity.setUpdateDate(formattedDate);
        this.customerDao.save(customerEntity);
        if (Objects.nonNull(customerEntity))return new SuccessResult(BusinessMessages.CustomerMessages.CUSTOMER_UPDATE_SUCCESS);
        return new ErrorResult(BusinessMessages.CustomerMessages.CUSTOMER_UPDATE_UNSUCCESS);
    }

    @Override
    public Result delete(DeleteCustomerRequest deleteCustomerRequest) {
        this.customerDao.deleteById(Math.toIntExact(deleteCustomerRequest.getCustomerId()));
        if (Objects.nonNull(deleteCustomerRequest.getCustomerId()))return new SuccessResult(BusinessMessages.CustomerMessages.CUSTOMER_DELETE_SUCCESS);
        return new ErrorResult(BusinessMessages.CustomerMessages.CUSTOMER_DELETE_UNSUCCESS);
    }

    public boolean checkCustomerIsExists(String nationalId){
        if(customerDao.existsCustomerEntitiesByNationalIdIgnoreCase(nationalId)) return true;
        return false;
    }
}
