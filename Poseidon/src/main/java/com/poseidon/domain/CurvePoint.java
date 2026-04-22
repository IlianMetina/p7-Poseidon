package com.poseidon.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "curvepoint")
@Getter
@NoArgsConstructor
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Setter
    private Integer curveId;

    @Setter
    private LocalDateTime asOfDate;

    @Setter
    private Double term;

    @Setter
    private Double value;

    @Setter
    private LocalDateTime creationDate;

}
