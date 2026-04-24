package com.poseidon.controllers;

import com.poseidon.domain.CurvePoint;
import com.poseidon.services.CurveService;
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

@WebMvcTest(CurveController.class)
@WithMockUser
public class CurveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CurveService curveService;

    private CurvePoint curvePoint;

    @BeforeEach
    public void setUp(){
        curvePoint = new CurvePoint();
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(5.0);
    }

    @Test
    void displayCurvePointListTest() throws Exception {

        when(curveService.findAllCurvePoints()).thenReturn(List.of(curvePoint));

        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/list"))
                .andExpect(model().attributeExists("curvePoint"));
    }

    @Test
    void addCurvePointFormTest() throws Exception{
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    void updateCurvePointTest() throws Exception {
        mockMvc.perform(post("/curvePoint/update/1")
                        .with(csrf())
                        .param("curveId", "1")
                        .param("term", "10.0")
                        .param("value", "5.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));


    }

    @Test
    void validateCurvePointTest() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    void deleteCurvePointTest() throws Exception {
        mockMvc.perform(get("/curvePoint/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    void showUpdateFormTest() throws Exception {
        when(curveService.findById(1)).thenReturn(curvePoint);
        mockMvc.perform(get("/curvePoint/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"))
                .andExpect(model().attributeExists("curvePoint"));
    }


}
