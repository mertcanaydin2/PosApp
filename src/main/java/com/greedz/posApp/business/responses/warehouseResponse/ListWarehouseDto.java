package com.greedz.posApp.business.responses.warehouseResponse;

import com.greedz.posApp.entities.CompanyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListWarehouseDto {
    private Long id;
    private String whouseName;
    private String address;
    private String telNo;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Long companyId;
}
