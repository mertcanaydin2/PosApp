package com.greedz.posApp.business.abstracts;

import com.greedz.posApp.business.requests.productRequests.CreateProductRequest;
import com.greedz.posApp.business.requests.productRequests.DeleteProductRequest;
import com.greedz.posApp.business.requests.productRequests.UpdateProductRequest;
import com.greedz.posApp.business.requests.userRequests.CreateUserRequest;
import com.greedz.posApp.business.requests.userRequests.DeleteUserRequest;
import com.greedz.posApp.business.requests.userRequests.UpdateUserRequest;
import com.greedz.posApp.business.responses.companyResponse.ListCompanyDto;
import com.greedz.posApp.business.responses.productResponses.ListProductDto;
import com.greedz.posApp.business.responses.userResponse.ListUserDto;
import com.greedz.posApp.core.utilities.results.DataResult;
import com.greedz.posApp.core.utilities.results.Result;

import java.util.List;

public interface UserService {

    DataResult<List<ListUserDto>> getAll();
    DataResult<List<ListUserDto>> getAllPaged(int pageNo, int pageSize);

    Result add(CreateUserRequest createUserRequest);
    Result update(UpdateUserRequest updateUserRequest);
    Result delete(DeleteUserRequest deleteUserRequest);
}
