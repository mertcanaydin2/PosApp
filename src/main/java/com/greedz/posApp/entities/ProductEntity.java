package com.greedz.posApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")

public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "productName")
    private String productName;
    @Column(name = "productNumber")
    private String productNumber;
    @Column(name = "price")
    private Long price;
    @Column(name = "createDate")
    private String createDate;

    @Column(name = "updateDate")
    private String updateDate;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private ProductCategoryEntity productCategoryEntity;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private CartEntity cartEntity;

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
