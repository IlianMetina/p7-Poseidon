package com.poseidon.services;

import com.poseidon.domain.CurvePoint;
import com.poseidon.repositories.CurvePointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurveService {

    private final CurvePointRepository curveRepo;

    public CurveService(CurvePointRepository curveRepo){
        this.curveRepo = curveRepo;
    }

    public List<CurvePoint> findAllCurvePoints(){
        List<CurvePoint> allCurvePoints = this.curveRepo.findAll();
        if(allCurvePoints.isEmpty()){
            throw new RuntimeException("Empty");
        }
        return allCurvePoints;
    }

    public CurvePoint updateCurvePoint(Integer id, CurvePoint curvePoint){
        CurvePoint curvePointToUpdate = this.curveRepo.findById(id).orElseThrow(() -> new RuntimeException("No curvepoint found"));
        curvePointToUpdate.setTerm(curvePoint.getTerm());
        curvePointToUpdate.setValue(curvePoint.getValue());
        curvePointToUpdate.setCreationDate(curvePoint.getCreationDate());
        curvePointToUpdate.setAsOfDate(curvePoint.getAsOfDate());
        curveRepo.save(curvePointToUpdate);
        return curvePointToUpdate;
    }

    public CurvePoint addCurvePoint(CurvePoint curvePoint){
        return this.curveRepo.save(curvePoint);
    }

    public CurvePoint findById(Integer id){
        return this.curveRepo.findById(id).orElseThrow(() -> new RuntimeException("No curvepoint found"));
    }

    public void deleteCurvePoint(CurvePoint curvePoint){
        this.curveRepo.delete(curvePoint);
    }

}
