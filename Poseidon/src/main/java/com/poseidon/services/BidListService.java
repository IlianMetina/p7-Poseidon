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

    public BidList addBid(BidList bidList){
        return this.bidListRepo.save(bidList);
    }

    public BidList updateBid(Integer id, BidList bidList){
        BidList bidToUpdate = this.bidListRepo.findById(id).orElseThrow(() -> new RuntimeException("BidList not found"));
        bidToUpdate.setAccount(bidList.getAccount());
        bidToUpdate.setType(bidList.getType());
        bidToUpdate.setBidQuantity(bidList.getBidQuantity());
        bidToUpdate.setAskQuantity(bidList.getAskQuantity());
        bidToUpdate.setBid(bidList.getBid());
        bidToUpdate.setAsk(bidList.getAsk());
        bidToUpdate.setBenchmark(bidList.getBenchmark());
        bidToUpdate.setBidListDate(bidList.getBidListDate());
        bidToUpdate.setCommentary(bidList.getCommentary());
        bidToUpdate.setSecurity(bidList.getSecurity());
        bidToUpdate.setStatus(bidList.getStatus());
        bidToUpdate.setTrader(bidList.getTrader());
        bidToUpdate.setBook(bidList.getBook());
        bidToUpdate.setCreationName(bidList.getCreationName());
        bidToUpdate.setCreationDate(bidList.getCreationDate());
        bidToUpdate.setRevisionName(bidList.getRevisionName());
        bidToUpdate.setRevisionDate(bidList.getRevisionDate());
        bidToUpdate.setDealName(bidList.getDealName());
        bidToUpdate.setDealType(bidList.getDealType());
        bidToUpdate.setSourceListId(bidList.getSourceListId());
        bidToUpdate.setSide(bidList.getSide());

        bidListRepo.save(bidToUpdate);

        return bidToUpdate;
    }

    public BidList findById(Integer id){
        return this.bidListRepo.findById(id).orElseThrow(() -> new RuntimeException("No bidlist match for this id"));
    }

    public void deleteBid(BidList bid){
        this.bidListRepo.delete(bid);
    }

}
