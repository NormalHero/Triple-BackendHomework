package com.avery.triplebackendhomework.domain.vo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRIPLE_PLACE")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"placeId"})
public class PlaceVO {

    @Id
    @Column(name ="PLACE_ID")
    private String placeId;


}
