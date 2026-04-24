package com.poseidon.services;

import com.poseidon.domain.RuleName;
import com.poseidon.repositories.RuleNameRepository;
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
public class RuleServiceTest {

    @Mock
    private RuleNameRepository ruleNameRepository;

    @InjectMocks
    private RuleNameService ruleNameService;

    private RuleName ruleName;

    @BeforeEach
    public void setUp(){
        ruleName = new RuleName();
        ruleName.setName("jean");
        ruleName.setTemplate("template");
        ruleName.setDescription("test");
        ruleName.setSqlStr("testsql");
    }

    @Test
    public void findAllRuleNamesTest(){
        when(ruleNameRepository.findAll()).thenReturn(List.of(ruleName));

        List<RuleName> result = ruleNameService.findAll();

        assertEquals(1, result.size());
        verify(ruleNameRepository, times(1)).findAll();
    }

    @Test
    public void updateRuleNameTest(){
        RuleName updated = new RuleName();
        updated.setName("jean");
        updated.setDescription("moi");
        updated.setTemplate("templatetest");

        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));
        when(ruleNameRepository.save(any(RuleName.class))).thenReturn(ruleName);

        RuleName result = ruleNameService.updateRuleName(1, updated);

        assertEquals("jean", result.getName());
        assertEquals("moi", result.getDescription());
        assertEquals("templatetest", result.getTemplate());

        verify(ruleNameRepository, times(1)).save(ruleName);
    }

    @Test
    public void addRuleNameTest(){
        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);

        RuleName result = ruleNameService.addRuleName(ruleName);
        assertNotNull(result);
        assertEquals("jean", result.getName());
        assertEquals("testsql", result.getSqlStr());

        verify(ruleNameRepository, times(1)).save(ruleName);
    }

    @Test
    public void findRuleNameByIdTest(){
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));
        RuleName result = ruleNameService.findById(1);
        assertNotNull(result);
        assertEquals("jean", result.getName());
        assertEquals("testsql", result.getSqlStr());
    }

    @Test
    public void deleteTradeTest(){
        doNothing().when(ruleNameRepository).delete(ruleName);
        ruleNameService.deleteRuleName(ruleName);
        verify(ruleNameRepository, times(1)).delete(ruleName);
    }

}
