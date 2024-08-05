package com.greedz.posApp.business.responses.productResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private Long categoryId;
    private Long customerId;
    private String productName;
    private String productNumber;
    private Long price;
    private Date createDate;
}
