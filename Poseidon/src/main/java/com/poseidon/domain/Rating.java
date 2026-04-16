package com.poseidon.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "rating")
@Getter
@NoArgsConstructor
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Setter
    private String moodysRating;
    @Setter
    private String sandPRating;
    @Setter
    private String fitchRating;
    @Setter
    private Integer orderNumber;
}
