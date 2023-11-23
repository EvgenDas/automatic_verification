package com.evgenygroupproject.spring.springboot.automatic_verification.entity;

import java.util.List;
import lombok.Data;

@Data
public class ResultVerification {

  private List<String> expected;
  private List<String> actual;
  private boolean result;

}
