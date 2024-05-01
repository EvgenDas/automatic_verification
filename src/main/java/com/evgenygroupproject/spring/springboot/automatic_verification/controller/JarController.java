package com.evgenygroupproject.spring.springboot.automatic_verification.controller;

import com.evgenygroupproject.spring.springboot.automatic_verification.config.MinioConfig;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Jar;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.ResultVerification;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Rule;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.JarService;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.RuleService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import java.io.IOException;
import java.io.InputStream;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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
@Tag(name="JarController", description="Класс для обработки jar-файлов")
public class JarController {

  @Autowired
  private RuleService ruleService;
  @Autowired
  private JarService jarService;
  @Autowired
  private MinioClient minioClient;

  @GetMapping
  @Operation(
          summary="страница для выбора правил",
          description="Страница для выбора правила"
  )
  public String pageForChoice(Model model) {
    Iterable<Rule> rules = ruleService.getAll();
    model.addAttribute("rules", rules);
    return "jar-init";
  }

  @PostMapping
  @Operation(
          summary = "работа с jar-файлами",
          description = "Работа с правилами, jar-файлами"
  )
  public String processJar(@RequestParam("jar_file") @NotEmpty MultipartFile jarFile,
                           @RequestParam("rule_id") @Min(0) int ruleId, Model model) throws IOException {

    createBucket();
    Jar jar = new Jar(java.util.UUID.randomUUID() + jarFile.getOriginalFilename(), ruleService.getById(ruleId).get());
    jarService.save(jar);

    saveFile(jarFile.getInputStream(), jar.getName());


    ResultVerification resultVerification = jarService.process(jar);
    String result;
    model.addAttribute("expected", resultVerification.getExpected());
    model.addAttribute("actual", resultVerification.getActual());
    result = resultVerification.isResult() ? "Правильное выполнение" : "Неправильное выполнение";
    model.addAttribute("result", result);



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
