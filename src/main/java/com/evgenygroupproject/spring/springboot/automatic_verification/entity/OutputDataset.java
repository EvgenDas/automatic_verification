package com.evgenygroupproject.spring.springboot.automatic_verification.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "output_datasets")
public class OutputDataset {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "file")
  private String file;

  @OneToOne(mappedBy = "outputDataset", cascade = CascadeType.ALL)
  private Rule outputRule;

}
