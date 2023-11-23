package com.evgenygroupproject.spring.springboot.automatic_verification.service.impl;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.InputDataset;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Jar;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.OutputDataset;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.ResultVerification;
import com.evgenygroupproject.spring.springboot.automatic_verification.repository.JarRepository;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.JarService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JarServiceImpl implements JarService {

  @Autowired
  private MinioClient minioClient;

  @Autowired
  private JarRepository jarRepository;

  @Override
  public void save(Jar jar) {
    jarRepository.save(jar);
  }

  @SneakyThrows
  @Override
  public ResultVerification process(Jar jar) {
    File directory = new File("src/main/resources/temporary_file");

    File outputFile = File.createTempFile("jar_write", ".csv", directory);

    File jarFile = File.createTempFile("jarFile", ".jar", directory);
    writeFile(jarFile, jar.getName(), "jar-files");

    List<File> listOfInputFile = new ArrayList<>();
    for (InputDataset dataset : jar.getRule().getInputDatasetList()) {
      File file = File.createTempFile("reader", ".csv", directory);
      writeFile(file, dataset.getName(), "cvs-files");
      listOfInputFile.add(file);
    }

    OutputDataset outputDataset = jar.getRule().getOutputDataset();
    File outputDatasetFile = File.createTempFile("writerFromRule", ".csv", directory);
    writeFile(outputDatasetFile, outputDataset.getName(), "cvs-files");

    readerJar(jarFile, outputFile, listOfInputFile);

    ResultVerification resultVerification = verification(outputDatasetFile, outputFile);

    outputFile.deleteOnExit();
    jarFile.deleteOnExit();
    listOfInputFile.forEach(File::deleteOnExit);
    outputDatasetFile.deleteOnExit();

    return resultVerification;

  }

  @SneakyThrows
  private ResultVerification verification(File expectedFile, File actualFile) {
    List<String> expected = new ArrayList<>();
    List<String> actual = new ArrayList<>();

    try(BufferedReader bufferedReaderExpected = new BufferedReader(new FileReader(expectedFile));
        BufferedReader bufferedReaderActual = new BufferedReader(new FileReader(actualFile))) {
      while(bufferedReaderExpected.ready()) {
        expected.add(bufferedReaderExpected.readLine());
      }

      while(bufferedReaderActual.ready()) {
        actual.add(bufferedReaderActual.readLine());
      }
    }
    ResultVerification result = new ResultVerification();
    result.setExpected(expected);
    result.setActual(actual);
    result.setResult(check(expected,actual));
    return result;
  }

  private boolean check(List<String> expected, List<String> actual) {
    if(expected.size() != actual.size()) {
      return false;
    }
    for(int i = 0; i < expected.size(); i++) {
      if(!expected.get(i).equals(actual.get(i))) {
        return false;
      }
    }
    return true;
  }

  @SneakyThrows
  public void readerJar(File jarFile, File outputFile, List<File> listOfInputFile) {
    String[] command = new String[2 + 2 + listOfInputFile.size()];
    command[0] = "java";
    command[1] = "-jar";
    command[2] = jarFile.getPath();
    command[3] = outputFile.getPath();
    for (int i = 0; i < listOfInputFile.size(); i++) {
      command[i + 4] = listOfInputFile.get(i).getPath();
    }
    ProcessBuilder builder = new ProcessBuilder(command);
    builder.redirectError(ProcessBuilder.Redirect.appendTo(outputFile));
    Process process = null;
    try {
      process = builder.start();
    } catch (IOException ex) {
      throw new IllegalArgumentException(ex);
    }

    process.waitFor();
  }

  @SneakyThrows
  private byte[] getInputStreamOfFile(String filename, String bucketName) {
    return minioClient.getObject(GetObjectArgs
        .builder()
        .bucket(bucketName)
        .object(filename)
        .build()).readAllBytes();
  }

  @SneakyThrows
  private void writeFile(File file, String filename, String bucketName) {
    byte[] allBytes = getInputStreamOfFile(filename, bucketName);
    try (FileOutputStream outputStream = new FileOutputStream(file)) {
      outputStream.write(allBytes);
    }
  }
}
