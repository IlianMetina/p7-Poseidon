package com.poseidon.services;

import com.poseidon.domain.Trade;
import com.poseidon.repositories.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeService tradeService;

    private Trade trade;

    @BeforeEach
    public void setUp(){
        trade = new Trade();
        trade.setAccount("TestAccount");
        trade.setType("TestType");
        trade.setBuyQuantity(10d);
    }

    @Test
    public void findAllTradesTest(){
        when(tradeRepository.findAll()).thenReturn(List.of(trade));

        List<Trade> result = tradeService.findAll();

        assertEquals(1, result.size());
        verify(tradeRepository, times(1)).findAll();
    }

    @Test
    public void addTradeTest(){
        when(tradeRepository.save(trade)).thenReturn(trade);

        Trade result = tradeService.addTrade(trade);
        assertNotNull(result);
        assertEquals("TestAccount", result.getAccount());
        verify(tradeRepository, times(1)).save(trade);
    }

    @Test
    public void updateRuleNameTest(){
        Trade updated = new Trade();
        updated.setAccount("AccountTesting");
        updated.setType("TypeTest");
        updated.setBuyQuantity(17.0);

        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));
        when(tradeRepository.save(any(Trade.class))).thenReturn(trade);

        Trade result = tradeService.updateTrade(1, updated);

        assertEquals("AccountTesting", result.getAccount());
        assertEquals("TypeTest", result.getType());
        assertEquals(17.0, result.getBuyQuantity());

        verify(tradeRepository, times(1)).save(trade);
    }

    @Test
    public void findTradesByIdTest(){
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));
        Trade result = tradeService.findById(1);
        assertNotNull(result);
        assertEquals("TestAccount", result.getAccount());
    }

    @Test
    public void deleteTradeTest(){
        doNothing().when(tradeRepository).delete(trade);
        tradeService.deleteTrade(trade);
        verify(tradeRepository, times(1)).delete(trade);
    }

}
