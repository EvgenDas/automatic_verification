package com.evgenygroupproject.spring.springboot.automatic_verification.controller;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.InputDataset;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.OutputDataset;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Rule;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.InputDatasetService;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.RuleService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.simpleframework.xml.core.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/api/rule")
@Validate
public class RuleController {

  @Autowired
  private RuleService ruleService;
  @Autowired
  private MinioClient minioClient;
  @Autowired
  private InputDatasetService inputDatasetService;

  @GetMapping("/{id}")
  public String getById(@PathVariable int id, Model model) {
    Rule rule = ruleService.getById(id).get();
    model.addAttribute("rule", rule);
    return "rule";
  }

  @GetMapping
  public String getRules(Model model) {
    Iterable<Rule> rules = ruleService.getAll();
    model.addAttribute("rules", rules);
    return "rules";
  }

  @PostMapping
  public String addRule(@RequestParam String name,
      @RequestParam("input_file") MultipartFile inputFile,
      @RequestParam("output_file") MultipartFile outputFile) throws IOException {

    InputDataset inputDataset = new InputDataset();
    inputDataset.setName(inputFile.getOriginalFilename());
    List<InputDataset> list = new ArrayList<>();
    list.add(inputDataset);

    OutputDataset outputDataset = new OutputDataset();
    outputDataset.setName(outputFile.getOriginalFilename());

    Rule rule = new Rule();
    rule.setName(name);
    rule.setInputDatasetList(list);
    rule.setOutputDataset(outputDataset);
    ruleService.save(rule);
    inputDataset.setRule(rule);
    inputDatasetService.save(inputDataset);





    createBucket();

    saveFile(inputFile.getInputStream(), inputFile.getOriginalFilename());
    saveFile(outputFile.getInputStream(), outputFile.getOriginalFilename());

    return "redirect:/api/rule";
  }


  @SneakyThrows
  private void createBucket() {
    boolean found = minioClient.bucketExists(BucketExistsArgs
        .builder()
        .bucket("cvs-files")
        .build());
    if (!found) {
      minioClient.makeBucket(MakeBucketArgs
          .builder()
          .build());
    }
  }

  @SneakyThrows
  private void saveFile(InputStream inputStream, String filename) {
    minioClient.putObject(PutObjectArgs
        .builder()
        .bucket("cvs-files")
        .object(filename)
        .stream(inputStream, inputStream.available(), -1)
        .build());
  }

}
