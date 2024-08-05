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
public class CreateUserRequest {
    @JsonIgnore
    private Long userId;
    @NotNull
    @Length(min = 1, max = 250)
    private String userName;
    @NotNull
    @Length(min = 1, max = 250)
    private String password;
    @NotNull
    @Length(min = 11,max = 11)
    private String nationalId;
    @NotNull
    @Length(min = 1, max = 250)
    private String userFirstName;
    @NotNull
    @Length(min = 1, max = 250)
    private String userLastName;
    @NotNull
    @Length(min = 1, max = 250)
    private String telNo;
    private LocalDateTime createDate;
}
