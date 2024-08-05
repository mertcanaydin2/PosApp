package com.greedz.posApp.api.controllers;

import com.greedz.posApp.business.abstracts.CustomerService;
import com.greedz.posApp.business.requests.customerRequests.CreateCustomerRequest;
import com.greedz.posApp.business.requests.customerRequests.DeleteCustomerRequest;
import com.greedz.posApp.business.requests.customerRequests.UpdateCustomerRequest;
import com.greedz.posApp.business.responses.customerResponse.ListCustomerDto;
import com.greedz.posApp.core.utilities.results.DataResult;
import com.greedz.posApp.core.utilities.results.Result;
import com.greedz.posApp.entities.CustomerEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCustomerRequest createCustomerRequest){
        return this.customerService.add(createCustomerRequest);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest){
        return this.customerService.update(updateCustomerRequest);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteCustomerRequest deleteCustomerRequest){
        return this.customerService.delete(deleteCustomerRequest);
    }

    @GetMapping("/getall")
    public DataResult<List<ListCustomerDto>> getAll(){
        return this.customerService.getAll();
    }

    @GetMapping("/getallpaged")
    public DataResult<List<ListCustomerDto>> getAllPaged(int pageNo, int pageSize){
        return this.customerService.getAllPaged(pageNo, pageSize);
    }

    @GetMapping("/getAllByFirstName")
    public DataResult<List<ListCustomerDto>> getAllByFirstName(@RequestParam("firstName") String firstName){
        return this.customerService.getAllByFirstName(firstName);
    }

    @GetMapping("/getAllById")
    public DataResult<List<ListCustomerDto>> getAllById(@RequestParam("customerId") Long customerId){
        return this.customerService.getAllById(customerId);
    }

}
