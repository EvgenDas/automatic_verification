package com.evgenygroupproject.spring.springboot.automatic_verification.service.impl;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.InputDataset;
import com.evgenygroupproject.spring.springboot.automatic_verification.repository.InputDatasetRepository;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.InputDatasetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Deprecated
@Service
public class InputDatasetServiceImpl implements InputDatasetService {
  @Autowired
  private InputDatasetRepository inputDatasetRepository;
  @Override
  public void save(List<InputDataset> inputDataset) {
    inputDataset.forEach(el -> inputDatasetRepository.save(el));
  }

}
