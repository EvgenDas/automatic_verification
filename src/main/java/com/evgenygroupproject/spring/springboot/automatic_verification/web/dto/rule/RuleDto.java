package com.evgenygroupproject.spring.springboot.automatic_verification.web.dto.rule;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RuleDto {

  private int id;

  @NotBlank(message = "id must be not blank")
  @Length(max = 255, message = "name length must be smaller than 255 symbols")
  private String name;
}
