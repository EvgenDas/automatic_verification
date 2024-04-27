package com.evgenygroupproject.spring.springboot.automatic_verification.service.impl;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Jar;
import com.evgenygroupproject.spring.springboot.automatic_verification.repository.JarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JarServiceImplTest {
    @Mock
    private JarRepository jarRepository;
    @InjectMocks
    private JarServiceImpl jarService;
    /*private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(jarService).build();
    }*/

    @Test
    void save() {
        Jar jar = new Jar();
        jarService.save(jar);
        verify(jarRepository, times(1)).save(jar);
    }

    @Test
    void readerJar() {
        File jarFile = new File("src/test/java/com/evgenygroupproject" +
                "/spring/springboot/automatic_verification/service/impl/testByPallindrome/pall.jar");
        File outputFule = new File("src/test/java/com/evgenygroupproject" +
                "/spring/springboot/automatic_verification/service/impl/testByPallindrome/Palindrome_writer.csv");
        File inputFile = new File("src/test/java/com/evgenygroupproject" +
                "/spring/springboot/automatic_verification/service/impl/testByPallindrome/Palindrome_reader.csv");

        List<File> listOfInputFiles = new ArrayList<>();
        listOfInputFiles.add(inputFile);

        List<String> listOfLinesInOutputFileExpected = new ArrayList<>();

        listOfLinesInOutputFileExpected.add("The number of Palindromes = 3");
        listOfLinesInOutputFileExpected.add("aaa");
        listOfLinesInOutputFileExpected.add("pap");
        listOfLinesInOutputFileExpected.add("zaz");

        jarService.readerJar(jarFile, outputFule, listOfInputFiles);

        List<String> listOfLinesInOutputFileActual = new ArrayList<>();
        try(BufferedReader bufferedReader = Files.newBufferedReader(Path.of("src/test/java/com/evgenygroupproject" +
                "/spring/springboot/automatic_verification/service/impl/testByPallindrome/Palindrome_writer.csv"))) {
            while(bufferedReader.ready()) {
            listOfLinesInOutputFileActual.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertArrayEquals(listOfLinesInOutputFileExpected.toArray(), listOfLinesInOutputFileActual.toArray());
    }

    @Test
    void readerJarThrowsIllegalArgumentException() {
        File jarFile = new File("Illegal");
        File outputFile = new File("");
        File inputFile = new File("");

        List<File> listOfInputFiles = new ArrayList<>();
        listOfInputFiles.add(inputFile);

        assertThrows(IllegalArgumentException.class, () -> jarService.readerJar(jarFile, outputFile, listOfInputFiles));
    }

    @Test
    void process() {}
}