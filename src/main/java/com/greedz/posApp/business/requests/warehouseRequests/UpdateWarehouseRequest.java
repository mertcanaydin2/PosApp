package com.greedz.posApp.business.requests.warehouseRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateWarehouseRequest {
    @JsonIgnore
    private Long id;
    @Length(min = 1, max = 250)
    private String whouseName;
    @Length(min = 1, max = 250)
    private String address;
    private String telNo;
    private LocalDateTime updateDate;
    @Length(min = 1, max = 250)
    private Long companyId;
}
