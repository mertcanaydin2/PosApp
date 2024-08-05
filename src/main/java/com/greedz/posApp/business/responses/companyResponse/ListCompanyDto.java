package com.greedz.posApp.business.responses.companyResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCompanyDto {

    private Long id;
    private String companyName;
    private String companyType;
    private String address;
    private String mail;
    private String telNo;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
