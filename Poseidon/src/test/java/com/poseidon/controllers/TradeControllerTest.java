package com.poseidon.controllers;

import com.poseidon.domain.RuleName;
import com.poseidon.domain.Trade;
import com.poseidon.services.RuleNameService;
import com.poseidon.services.TradeService;
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

@WebMvcTest(TradeController.class)
@WithMockUser
public class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TradeService tradeService;

    private Trade trade;

    @BeforeEach
    public void setUp(){
        trade = new Trade();
        trade.setAccount("TestAccount");
        trade.setType("TypeTest");
        trade.setCreationName("TestName");
    }

    @Test
    void displayTradeListTest() throws Exception {

        when(tradeService.findAll()).thenReturn(List.of(trade));

        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/list"))
                .andExpect(model().attributeExists("trades"));
    }

    @Test
    void addTradeFormTest() throws Exception{
        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }

    @Test
    void updateTradeTest() throws Exception {
        mockMvc.perform(post("/trade/update/1")
                        .with(csrf())
                        .param("account", "UpdatedAccount")
                        .param("type", "UpdatedType")
                        .param("creationName", "UpdatedName"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    void validateTradeTest() throws Exception {
        mockMvc.perform(post("/trade/validate")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    void deleteTradeTest() throws Exception {
        mockMvc.perform(get("/trade/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    void showUpdateFormTest() throws Exception {
        when(tradeService.findById(1)).thenReturn(trade);
        mockMvc.perform(get("/trade/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"))
                .andExpect(model().attributeExists("trade"));
    }


}
