package com.evgenygroupproject.spring.springboot.automatic_verification.repository;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Rule;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RuleRepository extends JpaRepository<Rule, Integer> {

  Optional<Rule> findByName(String name);
}
