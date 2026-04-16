package com.poseidon.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "rulename")
@Getter
@NoArgsConstructor
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Setter
    private String name;
    @Setter
    private String description;
    @Setter
    private String json;
    @Setter
    private String template;
    @Setter
    private String sqlStr;
    @Setter
    private String sqlPart;
}
