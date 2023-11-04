package com.evgenygroupproject.spring.springboot.automatic_verification.repository;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Jar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JarRepository extends JpaRepository<Jar, Integer> {

}
