package com.greedz.posApp.business.concretes;

import com.greedz.posApp.business.abstracts.CompanyService;
import com.greedz.posApp.business.abstracts.CustomerService;
import com.greedz.posApp.business.constants.BusinessMessages;
import com.greedz.posApp.business.requests.companyRequests.CreateCompanyRequest;
import com.greedz.posApp.business.requests.companyRequests.DeleteCompanyRequest;
import com.greedz.posApp.business.requests.companyRequests.UpdateCompanyRequest;
import com.greedz.posApp.business.requests.customerRequests.CreateCustomerRequest;
import com.greedz.posApp.business.requests.customerRequests.DeleteCustomerRequest;
import com.greedz.posApp.business.requests.customerRequests.UpdateCustomerRequest;
import com.greedz.posApp.business.responses.companyResponse.CompanyDto;
import com.greedz.posApp.business.responses.companyResponse.ListCompanyDto;
import com.greedz.posApp.business.responses.customerResponse.ListCustomerDto;
import com.greedz.posApp.core.utilities.mapping.ModelMapperService;
import com.greedz.posApp.core.utilities.results.*;
import com.greedz.posApp.dataAccess.abstracts.CompanyDao;
import com.greedz.posApp.entities.CompanyEntity;
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
public class CompanyManager implements CompanyService {
    private CompanyDao companyDao;
    private ModelMapperService modelMapperService;

    public CompanyManager(CompanyDao companyDao, ModelMapperService modelMapperService) {
        this.companyDao = companyDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<ListCompanyDto>> getAll() {
        List<CompanyEntity> companyEntities = this.companyDao.findAll();
        List<ListCompanyDto> response = companyEntities.stream()
                .map(companyEntity -> this.modelMapperService.forDto()
                        .map(companyEntity, ListCompanyDto.class))
                .collect(Collectors.toList());
        if (Objects.nonNull(response))return new SuccessDataResult<List<ListCompanyDto>>(response);
        return new ErrorDataResult<>(BusinessMessages.CompanyMessages.COMPANY_NOT_FOUND);    }

    @Override
    public DataResult<List<ListCompanyDto>> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);

        List<CompanyEntity> companyEntities = this.companyDao.findAll(pageable).getContent();
        List<ListCompanyDto> response = companyEntities.stream()
                .map(companyEntity -> this.modelMapperService.forDto()
                        .map(companyEntity,ListCompanyDto.class))
                .collect(Collectors.toList());
        if (Objects.nonNull(response)) return new SuccessDataResult<List<ListCompanyDto>>(response);

        return new ErrorDataResult<>(BusinessMessages.CompanyMessages.COMPANY_NOT_FOUND);
    }

    @Override
    public Result add(CreateCompanyRequest createCompanyRequest) {
        if (checkCompanyIsExists(createCompanyRequest.getCompanyName())) return new ErrorResult(BusinessMessages.CompanyMessages.COMPANY_IS_EXISTS);

        CompanyEntity companyEntity = this.modelMapperService.forRequest()
                .map(createCompanyRequest, CompanyEntity.class);

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        companyEntity.setCreateDate(formattedDate);
        this.companyDao.save(companyEntity);
        if (Objects.nonNull(companyEntity))return new SuccessResult(BusinessMessages.CompanyMessages.COMPANY_ADD_SUCCESS);
        return new ErrorResult(BusinessMessages.CompanyMessages.COMPANY_ADD_UNSUCCESS);    }

    @Override
    public Result update(UpdateCompanyRequest updateCompanyRequest) {
        CompanyEntity companyEntity = this.modelMapperService.forRequest()
                .map(updateCompanyRequest, CompanyEntity.class);

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        companyEntity.setUpdateDate(formattedDate);
        this.companyDao.save(companyEntity);
        if (Objects.nonNull(companyEntity))return new SuccessResult(BusinessMessages.CompanyMessages.COMPANY_UPDATE_SUCCESS);
        return new ErrorResult(BusinessMessages.CompanyMessages.COMPANY_UPDATE_UNSUCCESS);    }

    @Override
    public Result delete(DeleteCompanyRequest deleteCompanyRequest) {
        this.companyDao.deleteById(Math.toIntExact(deleteCompanyRequest.getId()));
        if (Objects.nonNull(deleteCompanyRequest.getId()))return new SuccessResult(BusinessMessages.CompanyMessages.COMPANY_DELETE_SUCCESS);
        return new ErrorResult(BusinessMessages.CompanyMessages.COMPANY_DELETE_UNSUCCESS);    }

    public boolean checkCompanyIsExists(String companyName){
        if(companyDao.existsByCompanyNameIgnoreCase(companyName)) return true;
        return false;
    }
}
