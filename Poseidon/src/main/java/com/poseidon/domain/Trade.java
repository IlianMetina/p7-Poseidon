package com.poseidon.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "trade")
@Getter
@Setter
@NoArgsConstructor
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer tradeId;

    @Setter
    private String account;

    @Setter
    private String type;

    @Setter
    private Double buyQuantity;

    @Setter
    private Double sellQuantity;

    @Setter
    private Double buyPrice;

    @Setter
    private Double sellPrice;

    @Setter
    private String benchmark;

    @Setter
    private LocalDateTime tradeDate;

    @Setter
    private String security;

    @Setter
    private String status;

    @Setter
    private String trader;

    @Setter
    private String book;

    @Setter
    private String creationName;

    @Setter
    private LocalDateTime creationDate;

    @Setter
    private String revisionName;

    @Setter
    private LocalDateTime revisionDate;

    @Setter
    private String dealName;

    @Setter
    private String dealType;

    @Setter
    private String sourceListId;

    @Setter
    private String side;
}

