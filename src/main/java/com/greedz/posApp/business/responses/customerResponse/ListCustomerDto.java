package com.greedz.posApp.business.responses.customerResponse;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCustomerDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nationalId;
    private String telNo;
    private String email;
    private String gender;
    private String custId;
    private String birthDate;
    private String address;
    private Long companyId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
