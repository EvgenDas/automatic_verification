package com.evgenygroupproject.spring.springboot.automatic_verification.service.impl;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.InputDataset;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Jar;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.OutputDataset;
import com.evgenygroupproject.spring.springboot.automatic_verification.repository.JarRepository;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.JarService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
  public void process(Jar jar) {
    File directory = new File("src/main/resources/temporary_file");

    File outputFile = File.createTempFile("jar_write", ".csv", directory);

    File jarFile = File.createTempFile(jar.getName(), ".jar", directory);
    writeFile(jarFile, jar.getName(), "jar-files");

    List<File> listOfInputFile = new ArrayList<>();
    for (InputDataset dataset : jar.getRule().getInputDatasetList()) {
      File file = File.createTempFile(dataset.getName(), ".csv", directory);
      writeFile(file, dataset.getName(), "cvs-files");
      listOfInputFile.add(file);
    }

    OutputDataset outputDataset = jar.getRule().getOutputDataset();
    File outputDatasetFile = File.createTempFile(outputDataset.getName(), ".csv", directory);
    writeFile(outputDatasetFile, outputDataset.getName(), "cvs-files");

    readerJar(jarFile, outputFile, listOfInputFile);

    outputFile.deleteOnExit();
    jarFile.deleteOnExit();
    listOfInputFile.forEach(File::deleteOnExit);
    outputDatasetFile.deleteOnExit();

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
