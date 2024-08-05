package com.greedz.posApp.dataAccess.abstracts;

import com.greedz.posApp.business.responses.customerResponse.ListCustomerDto;
import com.greedz.posApp.core.utilities.results.DataResult;
import com.greedz.posApp.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<CustomerEntity,Integer> {
    List<CustomerEntity> getAllByFirstName(String firstName);
    List<CustomerEntity> getAllById(Long customerId);
    boolean existsCustomerEntitiesByNationalIdIgnoreCase(String nationalId);

}
