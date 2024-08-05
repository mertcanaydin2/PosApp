package com.greedz.posApp.business.requests.customerRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {

    @JsonIgnore
    private Long customerId;
    @NotNull
    @Length(min = 1, max = 100)
    private String firstName;
    private String middleName;
    @NotNull
    @Length(min = 1, max = 100)
    private String lastName;
    @NotNull
    @Length(min = 11,max = 11)
    private String nationalId;
    private String telNo;
    private String email;
    private String gender;
    private String custId;
    private String birthDate;
    private String address;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
