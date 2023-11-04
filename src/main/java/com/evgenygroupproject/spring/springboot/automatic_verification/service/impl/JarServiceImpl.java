package com.evgenygroupproject.spring.springboot.automatic_verification.service.impl;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Jar;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Rule;
import com.evgenygroupproject.spring.springboot.automatic_verification.repository.JarRepository;
import com.evgenygroupproject.spring.springboot.automatic_verification.repository.RuleRepository;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.JarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JarServiceImpl implements JarService {

  @Autowired
  private JarRepository jarRepository;

  @Override
  public void save(Jar jar) {
    jarRepository.save(jar);
  }
}
