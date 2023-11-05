package com.evgenygroupproject.spring.springboot.automatic_verification.entity;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@RequiredArgsConstructor
@Entity
@Table(name = "rules")
public class Rule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
//  @NotBlank(message = "id must be not blank")
//  @Length(max = 255, message = "name length must be smaller than 255 symbols")
  private String name;

  @OneToMany(mappedBy = "rule", cascade = CascadeType.ALL)
  private List<InputDataset> inputDatasetList;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "output_datasets_id")
  private OutputDataset outputDataset;

  @OneToMany(mappedBy = "rule", cascade = CascadeType.ALL)
  private List<Jar> jars;

}
