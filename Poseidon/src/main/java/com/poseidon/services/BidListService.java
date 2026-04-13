package com.poseidon.services;

import com.poseidon.domain.BidList;
import com.poseidon.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListService {

    private final BidListRepository bidListRepo;

    public BidListService(BidListRepository bidListRepo){
        this.bidListRepo = bidListRepo;
    }

    public List<BidList> findAllBids(){
        return this.bidListRepo.findAll();
    }
}
