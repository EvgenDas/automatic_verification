package com.evgenygroupproject.spring.springboot.automatic_verification.controller;

import com.evgenygroupproject.spring.springboot.automatic_verification.entity.OutputDataset;
import com.evgenygroupproject.spring.springboot.automatic_verification.entity.Rule;
import com.evgenygroupproject.spring.springboot.automatic_verification.service.RuleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RuleControllerTest {

    @InjectMocks
    private RuleController ruleController;
    @Mock
    private RuleService ruleService;
    private Model model;
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
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> ruleController.getById(id,  model));
    }

    @Test
    void getRules() {
        model = new ExtendedModelMap();
        Rule rule = new Rule("rule", new ArrayList<>(), new OutputDataset());

        Mockito.when(ruleService.getAll()).thenReturn(Collections.singletonList(rule));

        assertEquals("rules", ruleController.getRules(model));
    }
}