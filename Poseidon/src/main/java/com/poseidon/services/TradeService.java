package com.poseidon.services;

import com.poseidon.domain.Trade;
import com.poseidon.repositories.TradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository){
        this.tradeRepository = tradeRepository;
    }

    public List<Trade> findAll(){
        return this.tradeRepository.findAll();
    }

    public Trade addTrade(Trade trade){
        return this.tradeRepository.save(trade);
    }

    public Trade updateTrade(Integer id, Trade trade){
        Trade tradeToUpdate = this.tradeRepository.findById(id).orElseThrow(() -> new RuntimeException("No Trade found"));
        tradeToUpdate.setAccount(trade.getAccount());
        tradeToUpdate.setCreationDate(trade.getCreationDate());
        tradeToUpdate.setTradeDate(trade.getTradeDate());
        tradeToUpdate.setBenchmark(trade.getBenchmark());
        tradeToUpdate.setBook(trade.getBook());
        tradeToUpdate.setBuyPrice(trade.getBuyPrice());
        tradeToUpdate.setDealName(trade.getDealName());
        tradeToUpdate.setStatus(trade.getStatus());
        tradeToUpdate.setType(trade.getType());
        tradeToUpdate.setBuyQuantity(trade.getBuyQuantity());
        tradeToUpdate.setRevisionDate(trade.getRevisionDate());
        tradeToUpdate.setSourceListId(trade.getSourceListId());
        tradeToUpdate.setTrader(trade.getTrader());
        tradeToUpdate.setSellQuantity(trade.getSellQuantity());
        tradeToUpdate.setSellPrice(trade.getSellPrice());
        tradeToUpdate.setSide(trade.getSide());
        tradeToUpdate.setSecurity(trade.getSecurity());
        tradeToUpdate.setDealType(trade.getDealType());
        tradeToUpdate.setRevisionName(trade.getRevisionName());
        tradeToUpdate.setCreationName(trade.getCreationName());

        this.tradeRepository.save(tradeToUpdate);

        return tradeToUpdate;
    }

    public Trade findById(Integer id){
        return this.tradeRepository.findById(id).orElseThrow(() -> new RuntimeException("No Trade found"));
    }

    public void deleteTrade(Trade trade){
        this.tradeRepository.delete(trade);
    }
}
