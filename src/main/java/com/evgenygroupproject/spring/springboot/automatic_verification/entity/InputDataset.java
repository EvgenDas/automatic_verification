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

@Data

@Entity
@Table(name = "input_datasets")
public class InputDataset {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "file")
  private String file;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "rule_id")
  private Rule rule;
}