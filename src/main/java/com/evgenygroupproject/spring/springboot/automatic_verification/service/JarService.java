package com.evgenygroupproject.spring.springboot.automatic_verification.service;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Jar;
import org.springframework.stereotype.Service;

@Service
public interface JarService {

  void save(Jar jar);
}
