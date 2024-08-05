package com.greedz.posApp.business.concretes;

import com.greedz.posApp.business.abstracts.WarehouseService;
import com.greedz.posApp.business.constants.BusinessMessages;
import com.greedz.posApp.business.requests.warehouseRequests.CreateWarehouseRequest;
import com.greedz.posApp.business.requests.warehouseRequests.DeleteWarehouseRequest;
import com.greedz.posApp.business.requests.warehouseRequests.UpdateWarehouseRequest;
import com.greedz.posApp.business.responses.warehouseResponse.ListWarehouseDto;
import com.greedz.posApp.core.utilities.mapping.ModelMapperService;
import com.greedz.posApp.core.utilities.results.*;
import com.greedz.posApp.dataAccess.abstracts.WarehouseDao;
import com.greedz.posApp.entities.WarehouseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class WarehouseManager implements WarehouseService {
    WarehouseDao warehouseDao;
    private ModelMapperService modelMapperService;

    public WarehouseManager(WarehouseDao warehouseDao, ModelMapperService modelMapperService) {
        this.warehouseDao = warehouseDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<ListWarehouseDto>> getAll() {
        List<WarehouseEntity> warehouseEntities = this.warehouseDao.findAll();
        List<ListWarehouseDto> response = warehouseEntities.stream()
                .map(warehouseEntity -> this.modelMapperService.forDto()
                        .map(warehouseEntity, ListWarehouseDto.class))
                .collect(Collectors.toList());
        if (Objects.nonNull(response))return new SuccessDataResult<List<ListWarehouseDto>>(response);
        return new ErrorDataResult<>(BusinessMessages.WarehouseMessages.WAREHOUSE_NOT_FOUND);
    }

    @Override
    public DataResult<List<ListWarehouseDto>> getAllPaged(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo -1, pageSize);

        List<WarehouseEntity> warehouseEntities = this.warehouseDao.findAll(pageable).getContent();
        List<ListWarehouseDto> response = warehouseEntities.stream()
                .map(warehouseEntity -> this.modelMapperService.forDto()
                        .map(warehouseEntity,ListWarehouseDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListWarehouseDto>>(response);
    }

    @Override
    public DataResult<List<ListWarehouseDto>> getAllByCompanyId(Long companyId) {
        List<WarehouseEntity> result = (List<WarehouseEntity>) this.warehouseDao.getAllByCompanyEntity_Id(companyId);
        List<ListWarehouseDto> response = result.stream()
                .map(warehouseEntity -> this.modelMapperService.forDto()
                        .map(warehouseEntity,ListWarehouseDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListWarehouseDto>>(response);

    }

    @Override
    public Result add(CreateWarehouseRequest createWarehouseRequest) {
        WarehouseEntity warehouseEntity = this.modelMapperService.forRequest()
                .map(createWarehouseRequest, WarehouseEntity.class);

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        warehouseEntity.setCreateDate(formattedDate);
        this.warehouseDao.save(warehouseEntity);
        if (Objects.nonNull(warehouseEntity))return new SuccessResult(BusinessMessages.WarehouseMessages.WAREHOUSE_ADD_SUCCESS);
        return new ErrorResult(BusinessMessages.WarehouseMessages.WAREHOUSE_ADD_UNSUCCESS);    }

    @Override
    public Result update(UpdateWarehouseRequest updateWarehouseRequest) {
        WarehouseEntity warehouseEntity = this.modelMapperService.forRequest()
                .map(updateWarehouseRequest, WarehouseEntity.class);

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        warehouseEntity.setUpdateDate(formattedDate);
        this.warehouseDao.save(warehouseEntity);
        if (Objects.nonNull(warehouseEntity))return new SuccessResult(BusinessMessages.WarehouseMessages.WAREHOUSE_UPDATE_SUCCESS);
        return new ErrorResult(BusinessMessages.WarehouseMessages.WAREHOUSE_UPDATE_UNSUCCESS);
    }

    @Override
    public Result delete(DeleteWarehouseRequest deleteWarehouseRequest) {
        this.warehouseDao.deleteById(deleteWarehouseRequest.getId());
        if (Objects.nonNull(deleteWarehouseRequest.getId()))return new SuccessResult(BusinessMessages.WarehouseMessages.WAREHOUSE_DELETE_SUCCESS);
        return new ErrorResult(BusinessMessages.WarehouseMessages.WAREHOUSE_DELETE_UNSUCCESS);
    }

}
