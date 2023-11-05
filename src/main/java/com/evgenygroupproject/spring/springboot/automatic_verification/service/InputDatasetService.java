package com.evgenygroupproject.spring.springboot.automatic_verification.service;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.InputDataset;
import org.springframework.stereotype.Service;

@Service
public interface InputDatasetService {

  void save(InputDataset inputDataset);

}
