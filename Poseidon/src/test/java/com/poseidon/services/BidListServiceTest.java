package com.poseidon.services;

import com.poseidon.domain.BidList;
import com.poseidon.repositories.BidListRepository;
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
public class BidListServiceTest {

    @Mock
    private BidListRepository bidListRepository;

    @InjectMocks
    private BidListService bidListService;

    private BidList bid;

    @BeforeEach
    public void setUp(){
        bid = new BidList();
        bid.setAccount("TestAccount");
        bid.setBidQuantity(10d);
        bid.setType("TestType");
    }

    @Test
    public void findAllBidsTest(){
        when(bidListRepository.findAll()).thenReturn(List.of(bid));

        List<BidList> result = bidListService.findAllBids();

        assertEquals(1, result.size());
        verify(bidListRepository, times(1)).findAll();
    }

    @Test
    public void addBidTest(){
        when(bidListRepository.save(bid)).thenReturn(bid);
        BidList result = bidListService.addBid(bid);

        assertNotNull(result);
        assertEquals("TestAccount", result.getAccount());
        verify(bidListRepository, times(1)).save(bid);
    }

    @Test
    public void updateBidTest(){
        BidList updated = new BidList();
        updated.setAccount("UpdatedAccount");
        updated.setType("UpdatedType");
        updated.setBidQuantity(99d);

        when(bidListRepository.findById(1)).thenReturn(Optional.of(bid));
        when(bidListRepository.save(any(BidList.class))).thenReturn(bid);

        BidList result = bidListService.updateBid(1, updated);

        assertEquals("UpdatedAccount", result.getAccount());
        assertEquals("UpdatedType", result.getType());
        assertEquals(99d, result.getBidQuantity());

        verify(bidListRepository, times(1)).save(bid);
    }

    @Test
    public void findByIdTest(){
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bid));
        BidList result = bidListService.findById(1);
        assertNotNull(result);
        assertEquals("TestAccount", result.getAccount());
    }

    @Test
    public void deleteBidTest(){
        doNothing().when(bidListRepository).delete(bid);
        bidListService.deleteBid(bid);

        verify(bidListRepository, times(1)).delete(bid);
    }
}
