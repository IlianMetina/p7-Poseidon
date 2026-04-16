package com.poseidon.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bidlist")
@Getter
@NoArgsConstructor
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BidListId")

    @Setter(AccessLevel.NONE)
    private Integer bidListId;

    @Setter
    private String account;

    @Setter
    private String type;

    @Setter
    private Double bidQuantity;

    @Setter
    private Double askQuantity;

    @Setter
    private Double bid;

    @Setter
    private Double ask;

    @Setter
    private String benchmark;

    @Setter
    private LocalDateTime bidListDate;

    @Setter
    private String commentary;

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
