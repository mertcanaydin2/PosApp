package com.greedz.posApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "companyName")
    private String companyName;
    @Column(name = "companyType")
    private String companyType;
    @Column(name = "address")
    private String address;
    @Column(name = "mail")
    private String mail;
    @Column(name = "telNo")
    private String telNo;
    @Column(name = "createDate")
    private String createDate;
    @Column(name = "updateDate")
    private String updateDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private List<CustomerEntity> customerEntities;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "party_id",referencedColumnName = "id")
    private List<UserEntity> userEntities;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "warehouse_id",referencedColumnName = "id")
    private List<WarehouseEntity> warehouseEntities;

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
