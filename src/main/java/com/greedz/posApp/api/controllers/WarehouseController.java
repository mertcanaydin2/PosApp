package com.greedz.posApp.api.controllers;

import com.greedz.posApp.business.abstracts.WarehouseService;
import com.greedz.posApp.business.requests.customerRequests.CreateCustomerRequest;
import com.greedz.posApp.business.requests.customerRequests.UpdateCustomerRequest;
import com.greedz.posApp.business.requests.warehouseRequests.CreateWarehouseRequest;
import com.greedz.posApp.business.requests.warehouseRequests.DeleteWarehouseRequest;
import com.greedz.posApp.business.requests.warehouseRequests.UpdateWarehouseRequest;
import com.greedz.posApp.business.responses.warehouseResponse.ListWarehouseDto;
import com.greedz.posApp.core.utilities.results.DataResult;
import com.greedz.posApp.core.utilities.results.Result;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateWarehouseRequest createWarehouseRequest){
        return this.warehouseService.add(createWarehouseRequest);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid UpdateWarehouseRequest updateWarehouseRequest){
        return this.warehouseService.update(updateWarehouseRequest);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteWarehouseRequest deleteWarehouseRequest){
        return this.warehouseService.delete(deleteWarehouseRequest);
    }

    @GetMapping("/getall")
    public DataResult<List<ListWarehouseDto>>getAll(){
        return this.warehouseService.getAll();
    }

    @GetMapping("/getAllByCompanyId")
    public DataResult<List<ListWarehouseDto>> getAllByCompanyId(@RequestParam("companyId") Long companyId){
        return (DataResult<List<ListWarehouseDto>>) this.warehouseService.getAllByCompanyId(companyId);
    }
}
