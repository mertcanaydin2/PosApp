package com.greedz.posApp.business.requests.userRequests;

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
public class UpdateUserRequest {
    @JsonIgnore
    private Long userId;
    @Length(min = 1, max = 250)
    private String userName;
    @Length(min = 1, max = 250)
    private String password;
    @Length(min = 11,max = 11)
    private String nationalId;
    @Length(min = 1, max = 250)
    private String userFirstName;
    @Length(min = 1, max = 250)
    private String userLastName;
    @Length(min = 1, max = 250)
    private String telNo;
    private LocalDateTime updateDate;
}
