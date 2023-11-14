package com.evgenygroupproject.spring.springboot.automatic_verification.controller;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.InputDataset;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.OutputDataset;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Rule;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.RuleService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
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

  @GetMapping("/{id}")
  public String getById(@PathVariable int id, Model model) {
    Optional<Rule> rule = ruleService.getById(id);
    if (rule.isPresent()) {
      model.addAttribute("rule", rule.get());
      return "rule";
    }
    throw new IllegalArgumentException("Такого правила не нашлось");
  }

  @GetMapping
  public String getRules(Model model) {
    Iterable<Rule> rules = ruleService.getAll();
    model.addAttribute("rules", rules);
    return "rules";
  }

  @PostMapping
  public String addRule(@RequestParam String name,
      @RequestParam("input_file") List<MultipartFile> inputFiles,
      @RequestParam("output_file") MultipartFile outputFile) throws IOException {

    List<InputDataset> inputDatasets = inputFiles.stream()
        .map(el -> new InputDataset(el.getOriginalFilename()))
        .toList();

    OutputDataset outputDataset = new OutputDataset(outputFile.getOriginalFilename());
    Rule rule = new Rule(name, inputDatasets, outputDataset);
    inputDatasets.forEach(el -> el.setRule(rule));

    ruleService.save(rule);

    createBucket();
    inputFiles.forEach(el -> {
      try {
        saveFile(el.getInputStream(), el.getOriginalFilename());
      } catch (IOException e) {
        throw new IllegalArgumentException("Добавление входных датасетов " + e);
      }
    });
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
