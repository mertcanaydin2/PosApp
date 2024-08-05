package com.greedz.posApp.business.requests.productRequests;

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
public class CreateProductRequest {
    @JsonIgnore
    private Long productId;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long customerId;
    @NotNull
    @Length(min = 1, max = 250)
    private String productName;
    @NotNull
    private String productNumber;
    @NotNull
    private Long price;
    private LocalDateTime createDate;

}

