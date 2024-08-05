package com.greedz.posApp.business.responses.userResponse;

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
public class ListUserDto {

    private Long id;
    private String userName;
    private String password;
    private String nationalId;
    private String userFirstName;
    private String userLastName;
    private String telNo;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Long companyId;
}
