package com.greedz.posApp.business.abstracts;

import com.greedz.posApp.business.requests.customerRequests.CreateCustomerRequest;
import com.greedz.posApp.business.requests.customerRequests.DeleteCustomerRequest;
import com.greedz.posApp.business.requests.customerRequests.UpdateCustomerRequest;
import com.greedz.posApp.business.responses.customerResponse.ListCustomerDto;
import com.greedz.posApp.core.utilities.results.DataResult;
import com.greedz.posApp.core.utilities.results.Result;
import com.greedz.posApp.entities.CustomerEntity;

import java.util.List;

public interface CustomerService {

    DataResult<List<ListCustomerDto>> getAll();
    DataResult<List<ListCustomerDto>> getAllPaged(int pageNo, int pageSize);

    DataResult<List<ListCustomerDto>> getAllByFirstName(String firstName);
    DataResult<List<ListCustomerDto>> getAllById(Long customerId);
    Result add(CreateCustomerRequest createCustomerRequest);
    Result update(UpdateCustomerRequest updateCustomerRequest);
    Result delete(DeleteCustomerRequest deleteCustomerRequest);
}
