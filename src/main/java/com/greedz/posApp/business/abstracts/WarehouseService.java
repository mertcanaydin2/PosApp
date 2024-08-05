package com.greedz.posApp.business.abstracts;

import com.greedz.posApp.business.requests.userRequests.CreateUserRequest;
import com.greedz.posApp.business.requests.userRequests.DeleteUserRequest;
import com.greedz.posApp.business.requests.userRequests.UpdateUserRequest;
import com.greedz.posApp.business.requests.warehouseRequests.CreateWarehouseRequest;
import com.greedz.posApp.business.requests.warehouseRequests.DeleteWarehouseRequest;
import com.greedz.posApp.business.requests.warehouseRequests.UpdateWarehouseRequest;
import com.greedz.posApp.business.responses.userResponse.ListUserDto;
import com.greedz.posApp.business.responses.warehouseResponse.ListWarehouseDto;
import com.greedz.posApp.core.utilities.results.DataResult;
import com.greedz.posApp.core.utilities.results.Result;

import java.util.List;

public interface WarehouseService {

    DataResult<List<ListWarehouseDto>> getAll();
    DataResult<List<ListWarehouseDto>> getAllPaged(int pageNo, int pageSize);
    DataResult<List<ListWarehouseDto>> getAllByCompanyId(Long companyId);

    Result add(CreateWarehouseRequest createWarehouseRequest);
    Result update(UpdateWarehouseRequest updateWarehouseRequest);
    Result delete(DeleteWarehouseRequest deleteWarehouseRequest);
}
