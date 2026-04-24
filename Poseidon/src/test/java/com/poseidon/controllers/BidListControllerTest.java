package com.poseidon.controllers;

import com.poseidon.domain.BidList;
import com.poseidon.services.BidListService;
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

@WebMvcTest(BidListController.class)
@WithMockUser
public class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BidListService bidListService;

    private BidList bid;

    @BeforeEach
    public void setUp(){
        bid = new BidList();
        bid.setAccount("TestAccount");
        bid.setType("TestType");
        bid.setBidQuantity(10.0);
    }

    @Test
    void displayBidListTest() throws Exception {

        when(bidListService.findAllBids()).thenReturn(List.of(bid));

        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"))
                .andExpect(model().attributeExists("bids"));
    }

    @Test
    void addBidFormTest() throws Exception{
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    void updateBidTest() throws Exception {
        mockMvc.perform(post("/bidList/update/1")
                .with(csrf())
                .param("account", "TestAccount")
                .param("type", "TestType")
                .param("bidQuantity", "10.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));


    }

    @Test
    void validateBidTest() throws Exception {
        mockMvc.perform(post("/bidList/validate")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    void deleteBidTest() throws Exception {
        mockMvc.perform(get("/bidList/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    void showUpdateFormTest() throws Exception {
        when(bidListService.findById(1)).thenReturn(bid);
        mockMvc.perform(get("/bidList/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"))
                .andExpect(model().attributeExists("bidList"));
    }


}
