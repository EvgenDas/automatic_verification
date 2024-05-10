package com.evgenygroupproject.spring.springboot.automatic_verification.controller;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.OutputDataset;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Rule;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.RuleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.MinioClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@ExtendWith(MockitoExtension.class)
class RuleControllerTest {

    @InjectMocks
    private RuleController ruleController;
    @Mock
    private RuleService ruleService;
    private Model model;
    @Mock
    MinioClient minioClient;
    @Test
    void getById() {
        int id = 1;
        model = new ExtendedModelMap();
        Optional<Rule> rule = Optional.of(new Rule("rule",
                new ArrayList<>(), new OutputDataset()));

        Mockito.when(ruleService.getById(id)).thenReturn(rule);

        Assertions.assertEquals("rule", ruleController.getById(id, model));
    }
    @Test
    void getByIdThrowsIllegalArgumentException() {
        int id = 1;
        try {
            ruleController.getById(id,  model);
        } catch (IllegalArgumentException e) {
            assertEquals("Такого правила не нашлось", e.getMessage());
            return;
        }
        fail("Expected validation exception was not thrown");
    }

    @Test
    void getRules() {
        model = new ExtendedModelMap();
        Rule rule = new Rule("rule", new ArrayList<>(), new OutputDataset());

        Mockito.when(ruleService.getAll()).thenReturn(Collections.singletonList(rule));

        assertEquals("rules", ruleController.getRules(model));
    }

    @Test
    void addRule() throws IOException {
        String name = "test";
        File inputFile1 = new File("src/test/java/com/evgenygroupproject/spring/springboot" +
                "/automatic_verification/controller/input1");
        File inputFile2 = new File("src/test/java/com/evgenygroupproject/spring/springboot" +
                "/automatic_verification/controller/input2");
        File outputFile = new File("src/test/java/com/evgenygroupproject/spring/springboot" +
                "/automatic_verification/controller/output");
        byte[] byteOfInput1 = getBytesOfFile(inputFile1);
        byte[] byteOfInput2 = getBytesOfFile(inputFile2);
        byte[] byteOfOutput = getBytesOfFile(outputFile);

        MockMultipartFile input1 = new MockMultipartFile("inputFile1", byteOfInput1);
        MockMultipartFile input2 = new MockMultipartFile("inputFile2", byteOfInput2);
        MockMultipartFile output = new MockMultipartFile("output", byteOfOutput);

        List<MultipartFile> multipartFileList = new ArrayList<>();
        multipartFileList.add(input1);
        multipartFileList.add(input2);

        assertEquals("redirect:/api/rule", ruleController.addRule(name, multipartFileList, output));
        verify(ruleService, times(1)).save(any());




    }
    private byte[] getBytesOfFile(File file) throws FileNotFoundException {
        byte[] bytes = new byte[(int) file.length()];
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
           fileInputStream.read(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }
}