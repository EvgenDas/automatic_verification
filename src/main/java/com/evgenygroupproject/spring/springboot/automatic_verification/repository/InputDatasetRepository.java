package com.evgenygroupproject.spring.springboot.automatic_verification.repository;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.InputDataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputDatasetRepository extends JpaRepository<InputDataset, Integer> {

}
