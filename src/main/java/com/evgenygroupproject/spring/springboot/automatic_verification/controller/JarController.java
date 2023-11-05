package com.evgenygroupproject.spring.springboot.automatic_verification.controller;

import com.evgenygroupproject.spring.springboot.automatic_verification.config.MinioConfig;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Jar;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Rule;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.JarService;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.RuleService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import java.io.IOException;
import java.io.InputStream;
import lombok.SneakyThrows;
import org.simpleframework.xml.core.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/jar")
@Validate
public class JarController {

  @Autowired
  private RuleService ruleService;
  @Autowired
  private JarService jarService;
  @Autowired
  private MinioClient minioClient;

  @GetMapping
  public String pageForChoice(Model model) {
    Iterable<Rule> rules = ruleService.getAll();
    model.addAttribute("rules", rules);
    return "jar-init";
  }

  @PostMapping
  public String processJar(@RequestParam("jar_file") MultipartFile jarFile,
      @RequestParam("rule_id") int ruleId) throws IOException {

    createBucket();
    Jar jar = new Jar(jarFile.getOriginalFilename(), ruleService.getById(ruleId).get());
    jarService.save(jar);

    saveFile(jarFile.getInputStream(), jar.getName());

    jarService.process(jar);


    return "process";
  }

  @SneakyThrows
  private void createBucket() {
    boolean found = minioClient.bucketExists(BucketExistsArgs
        .builder()
        .bucket("jar-files")
        .build());
    if (!found) {
      minioClient.makeBucket(MakeBucketArgs
          .builder()
          .bucket("jar-files")
          .build());
    }
  }

  @SneakyThrows
  private void saveFile(InputStream inputStream, String filename) {
    minioClient.putObject(PutObjectArgs
        .builder()
        .bucket("jar-files")
        .object(filename)
        .stream(inputStream, inputStream.available(), -1)
        .build());
  }
}
