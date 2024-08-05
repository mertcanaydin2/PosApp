package com.greedz.posApp.business.responses.companyResponse;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private Long id;
    private String companyName;
    private String companyType;
    private String address;
    private String mail;
    private String telNo;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
