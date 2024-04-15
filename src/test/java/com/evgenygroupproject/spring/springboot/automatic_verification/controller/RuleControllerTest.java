package com.evgenygroupproject.spring.springboot.automatic_verification.controller;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.OutputDataset;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Rule;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.RuleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@ExtendWith(MockitoExtension.class)
class RuleControllerTest {

    @InjectMocks
    private RuleController ruleController;
    @Mock
    private RuleService ruleService;
    private Model model;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
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
    void addRule() throws Exception {
        String name = "testRule";
        mockMvc = MockMvcBuilders.standaloneSetup(ruleController).build();
        objectMapper = new ObjectMapper();

        Rule rule = new Rule(name, new ArrayList<>(), new OutputDataset());
        String ruleJson = objectMapper.writeValueAsString(rule);

        mockMvc.perform(post("/api/rule/addRule")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ruleJson))
                .andReturn();
    }
}