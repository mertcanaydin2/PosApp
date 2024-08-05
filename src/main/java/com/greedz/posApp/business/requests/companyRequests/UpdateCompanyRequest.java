package com.greedz.posApp.business.requests.companyRequests;

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
public class UpdateCompanyRequest {
    @JsonIgnore
    private Long id;
    @Length(min = 1, max = 100)
    private String companyName;
    @Length(min = 1, max = 100)
    private String companyType;
    private String address;
    private String mail;
    private String telNo;
    private LocalDateTime updateDate;
}
