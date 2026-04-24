package com.poseidon.controllers;

import com.poseidon.domain.CurvePoint;
import com.poseidon.domain.RuleName;
import com.poseidon.services.CurveService;
import com.poseidon.services.RuleNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RuleNameController.class)
@WithMockUser
public class RuleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RuleNameService ruleNameService;

    private RuleName ruleName;

    @BeforeEach
    public void setUp(){
        ruleName = new RuleName();
        ruleName.setName("TestName");
        ruleName.setDescription("DescriptionTest");
        ruleName.setTemplate("TestTemplate");
    }

    @Test
    void displayRuleNameListTest() throws Exception {

        when(ruleNameService.findAll()).thenReturn(List.of(ruleName));

        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/list"))
                .andExpect(model().attributeExists("rulesNames"));
    }

    @Test
    void addRuleNameFormTest() throws Exception{
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    void updateRuleNameTest() throws Exception {
        mockMvc.perform(post("/ruleName/update/1")
                        .with(csrf())
                        .param("name", "NameTest")
                        .param("description", "UpdatedDescription")
                        .param("template", "UpdatedTemplate"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    void validateRuleNameTest() throws Exception {
        mockMvc.perform(post("/ruleName/validate")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    void deleteRuleNameTest() throws Exception {
        mockMvc.perform(get("/ruleName/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    void showUpdateFormTest() throws Exception {
        when(ruleNameService.findById(1)).thenReturn(ruleName);
        mockMvc.perform(get("/ruleName/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"))
                .andExpect(model().attributeExists("ruleName"));
    }


}
