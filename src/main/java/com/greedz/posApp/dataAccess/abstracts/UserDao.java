package com.greedz.posApp.dataAccess.abstracts;

import com.greedz.posApp.entities.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {
    boolean existsUserEntitiesByNationalIdIgnoreCase(String nationalId);
}
