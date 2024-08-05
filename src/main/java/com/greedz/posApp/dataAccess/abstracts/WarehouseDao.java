package com.greedz.posApp.dataAccess.abstracts;

import com.greedz.posApp.business.responses.warehouseResponse.ListWarehouseDto;
import com.greedz.posApp.core.utilities.results.DataResult;
import com.greedz.posApp.entities.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseDao extends JpaRepository<WarehouseEntity,Long> {
    DataResult<List<WarehouseEntity>> getAllByCompanyEntity_Id(Long companyId);
}