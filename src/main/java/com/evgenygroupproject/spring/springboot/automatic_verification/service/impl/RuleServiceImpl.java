package com.evgenygroupproject.spring.springboot.automatic_verification.service.impl;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Rule;
import com.evgenygroupproject.spring.springboot.automatic_verification.repository.RuleRepository;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.RuleService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleServiceImpl implements RuleService {
  @Autowired
  private RuleRepository ruleRepository;

  @Override
  public Optional<Rule> getById(int id) {
    return ruleRepository.findById(id);
  }

  @Override
  public Optional<Rule> getByName(String name) {
    return ruleRepository.findByName(name);
  }

  @Override
  public List<Rule> getAll() {
    return ruleRepository.findAll();
  }

  @Override
  public void save(Rule rule) {
    ruleRepository.save(rule);
  }
}
