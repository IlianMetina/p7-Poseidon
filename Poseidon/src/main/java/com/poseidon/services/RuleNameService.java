package com.poseidon.services;

import com.poseidon.domain.RuleName;
import com.poseidon.repositories.RuleNameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameService {

    private final RuleNameRepository ruleNameRepository;

    public RuleNameService(RuleNameRepository ruleNameRepository){
        this.ruleNameRepository = ruleNameRepository;
    }

    public List<RuleName> findAll(){
        return this.ruleNameRepository.findAll();
    }

    public RuleName addRuleName(RuleName ruleName){
        return this.ruleNameRepository.save(ruleName);
    }

    public RuleName updateRuleName(Integer id, RuleName ruleName){
        RuleName rulenameToUpdate = this.ruleNameRepository.findById(id).orElseThrow(() -> new RuntimeException("No RuleName found"));
        rulenameToUpdate.setName(ruleName.getName());
        rulenameToUpdate.setJson(ruleName.getJson());
        rulenameToUpdate.setDescription(ruleName.getDescription());
        rulenameToUpdate.setTemplate(ruleName.getTemplate());
        rulenameToUpdate.setSqlStr(ruleName.getSqlStr());
        rulenameToUpdate.setSqlPart(ruleName.getSqlPart());

        this.ruleNameRepository.save(rulenameToUpdate);

        return rulenameToUpdate;
    }

    public RuleName findById(Integer id){
        return this.ruleNameRepository.findById(id).orElseThrow(() -> new RuntimeException("No RuleName found"));
    }

    public void deleteRuleName(RuleName ruleName){
        this.ruleNameRepository.delete(ruleName);
    }
}
