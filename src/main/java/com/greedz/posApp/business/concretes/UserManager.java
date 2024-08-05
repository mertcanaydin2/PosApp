package com.greedz.posApp.business.concretes;

import com.greedz.posApp.business.abstracts.UserService;
import com.greedz.posApp.business.constants.BusinessMessages;
import com.greedz.posApp.business.requests.userRequests.CreateUserRequest;
import com.greedz.posApp.business.requests.userRequests.DeleteUserRequest;
import com.greedz.posApp.business.requests.userRequests.UpdateUserRequest;
import com.greedz.posApp.business.responses.customerResponse.ListCustomerDto;
import com.greedz.posApp.business.responses.userResponse.ListUserDto;
import com.greedz.posApp.core.utilities.mapping.ModelMapperService;
import com.greedz.posApp.core.utilities.results.*;
import com.greedz.posApp.dataAccess.abstracts.UserDao;
import com.greedz.posApp.entities.CustomerEntity;
import com.greedz.posApp.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserService {
    UserDao userDao;
    ModelMapperService modelMapperService;

    public UserManager(UserDao userDao, ModelMapperService modelMapperService) {
        this.userDao = userDao;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public DataResult<List<ListUserDto>> getAll() {
        List<UserEntity> userEntities = this.userDao.findAll();
        List<ListUserDto> response = userEntities.stream()
                .map(userEntity -> this.modelMapperService.forDto()
                        .map(userEntity, ListUserDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListUserDto>>(response);
    }

    @Override
    public DataResult<List<ListUserDto>> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);

        List<UserEntity> userEntities = this.userDao.findAll(pageable).getContent();
        List<ListUserDto> response = userEntities.stream()
                .map(userEntity -> this.modelMapperService.forDto()
                        .map(userEntity, ListUserDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListUserDto>>(response);
    }

    @Override
    public Result add(CreateUserRequest createUserRequest) {
        if(checkUserIsExists(createUserRequest.getNationalId())) return new ErrorResult(BusinessMessages.UserMessages.USER_IS_EXISTS);
        //TODO
//        UserEntity userEntity1 = new UserEntity();
//        partyId.setColumn = (userEntity1.getId());
        UserEntity userEntity = this.modelMapperService.forRequest()
                .map(createUserRequest, UserEntity.class);

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        userEntity.setCreateDate(formattedDate);
        this.userDao.save(userEntity);
        if (Objects.nonNull(userEntity)) return new SuccessResult(BusinessMessages.UserMessages.USER_ADD_SUCCESS);

        return new ErrorResult(BusinessMessages.UserMessages.USER_ADD_UNSUCCESS);
    }

    @Override
    public Result update(UpdateUserRequest updateUserRequest) {
        UserEntity userEntity = this.modelMapperService.forRequest()
                .map(updateUserRequest, UserEntity.class);

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        userEntity.setUpdateDate(formattedDate);
        this.userDao.save(userEntity);
        if (Objects.nonNull(userEntity)) return new SuccessResult(BusinessMessages.UserMessages.USER_UPDATE_SUCCESS);

        return new ErrorResult(BusinessMessages.UserMessages.USER_UPDATE_UNSUCCESS);
    }

    @Override
    public Result delete(DeleteUserRequest deleteUserRequest) {
        this.userDao.deleteById(Math.toIntExact(deleteUserRequest.getUserId()));
        if (Objects.nonNull(deleteUserRequest.getUserId())) return new SuccessResult(BusinessMessages.UserMessages.USER_DELETE_SUCCESS);
        return new ErrorResult(BusinessMessages.UserMessages.USER_DELETE_UNSUCCESS);
    }
    public boolean checkUserIsExists(String nationalId){
        if(userDao.existsUserEntitiesByNationalIdIgnoreCase(nationalId)) return true;
        return false;
    }
}
