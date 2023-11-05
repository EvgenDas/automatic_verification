package com.evgenygroupproject.spring.springboot.automatic_verification.service;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Rule;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface RuleService {
  Optional<Rule> getById(int id);

  Optional<Rule> getByName(String name);

  List<Rule> getAll();

  void save(Rule rule);

}
