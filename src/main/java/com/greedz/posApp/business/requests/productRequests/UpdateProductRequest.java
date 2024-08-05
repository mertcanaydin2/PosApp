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
public class UpdateProductRequest {

    @JsonIgnore
    private Long productId;
    private Long categoryId;
    private Long customerId;
    @Length(min = 1, max = 250)
    private String productName;
    private String productNumber;
    private Long price;
    private LocalDateTime updateDate;

}
