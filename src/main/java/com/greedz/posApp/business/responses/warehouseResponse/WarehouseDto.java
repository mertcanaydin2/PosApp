package com.greedz.posApp.business.responses.warehouseResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDto {
    private Long id;
    private String whouseName;
    private String address;
    private String telNo;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
