package com.evgenygroupproject.spring.springboot.automatic_verification.controller;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.OutputDataset;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Jar;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.ResultVerification;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Rule;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.JarService;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.RuleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import io.minio.MinioClient;
import java.io.File;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class JarControllerTest {

    @InjectMocks
    private JarController jarController;
    @Mock
    private JarService jarService;
    @Mock
    private MinioClient minioClient;
    @Mock
    private RuleService ruleService;
    @Mock
    private ResultVerification resultVerification;
    private Model model;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void pageForChoice(){

        model = new ExtendedModelMap();
        Rule rule = new Rule("rule", new ArrayList<>(), new OutputDataset());

        Mockito.when(ruleService.getAll()).thenReturn(Collections.singletonList(rule));

        assertEquals("jar-init", jarController.pageForChoice(model));

    }

    @Test
    public void ProcessJar() throws Exception {

        model = new ExtendedModelMap();
        mockMvc = MockMvcBuilders.standaloneSetup(jarController).build();
        Optional<Rule> rule = Optional.of(new Rule("rule", new ArrayList<>(), new OutputDataset()));
        int ruleId = 1;

        when(ruleService.getById(ruleId)).thenReturn(rule);
        when(minioClient.bucketExists(any())).thenReturn(true);

        resultVerification = new ResultVerification();
        when(jarService.process(any())).thenReturn(resultVerification);

        MockMultipartFile multipartFile = new MockMultipartFile("jar_file", "filename.jar", "text/plain", "data".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/jar")
                        .file(multipartFile)
                        .param("rule_id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("result"))
                .andExpect(view().name("process"));

        verify(jarService, times(1)).process(any());
    }

}