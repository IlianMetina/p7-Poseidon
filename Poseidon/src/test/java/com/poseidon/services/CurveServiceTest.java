package com.poseidon.services;

import com.poseidon.domain.CurvePoint;
import com.poseidon.repositories.CurvePointRepository;
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
public class CurveServiceTest {

    @Mock
    private CurvePointRepository curvePointRepository;

    @InjectMocks
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
    public void findAllCurvePointsTest(){
        when(curvePointRepository.findAll()).thenReturn(List.of(curvePoint));

        List<CurvePoint> result = curveService.findAllCurvePoints();

        assertEquals(1, result.size());
        verify(curvePointRepository, times(1)).findAll();
    }

    @Test
    public void addCurvePointTest(){
        when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);
        CurvePoint result = curveService.addCurvePoint(curvePoint);
        assertNotNull(result);
        assertEquals(1, result.getCurveId());
        verify(curvePointRepository, times(1)).save(curvePoint);
    }

    @Test
    public void updateCurvePointTest(){
        CurvePoint updated = new CurvePoint();
        updated.setCurveId(2);
        updated.setTerm(99.0);
        updated.setValue(50.0);

        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));
        when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(curvePoint);

        CurvePoint result = curveService.updateCurvePoint(1, updated);

        assertEquals(2, result.getCurveId());
        assertEquals(99.0, result.getTerm());
        assertEquals(50.0, result.getValue());
        verify(curvePointRepository, times(1)).save(curvePoint);
    }

    @Test
    public void findCurvePointByIdTest(){
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));
        CurvePoint result = curveService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getCurveId());
    }

    @Test
    public void deleteCurvePointTest(){
        doNothing().when(curvePointRepository).delete(curvePoint);
        curveService.deleteCurvePoint(curvePoint);
        verify(curvePointRepository, times(1)).delete(curvePoint);
    }
}
