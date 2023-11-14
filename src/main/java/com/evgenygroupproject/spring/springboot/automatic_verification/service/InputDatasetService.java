package com.evgenygroupproject.spring.springboot.automatic_verification.service;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.InputDataset;
import java.util.List;
import org.springframework.stereotype.Service;

@Deprecated
@Service
public interface InputDatasetService {

  void save(List<InputDataset> inputDataset);

}
