package com.evgenygroupproject.spring.springboot.automatic_verification.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "jars")
public class Jar {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "rule_id")
  private Rule rule;

  public Jar(String name, Rule rule) {
    this.name = name;
    this.rule = rule;
  }

}
