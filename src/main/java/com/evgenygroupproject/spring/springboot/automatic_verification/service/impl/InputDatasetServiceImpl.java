package com.evgenygroupproject.spring.springboot.automatic_verification.service.impl;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.InputDataset;
import com.evgenygroupproject.spring.springboot.automatic_verification.repository.InputDatasetRepository;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.InputDatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputDatasetServiceImpl implements InputDatasetService {
  @Autowired
  private InputDatasetRepository inputDatasetRepository;
  @Override
  public void save(InputDataset inputDataset) {
    inputDatasetRepository.save(inputDataset);
  }

}
