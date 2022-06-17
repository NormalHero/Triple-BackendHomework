package com.avery.triplebackendhomework.domain.vo;


import com.avery.triplebackendhomework.domain.dto.EventDTO;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRIPLE_REVIEW")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"reviewId","content","reviewCreatedTime","reviewUpdateTime"})
public class ReviewVO {

    @Id
    @Column(name = "REVIEW_ID")
    private String reviewId;
    @Column(name = "CONTENT")
    private String content;
    @Generated(GenerationTime.INSERT)
    @Column(name = "REVIEW_CREATEDTIME")
    private Date reviewCreatedTime;
    @Generated(GenerationTime.ALWAYS)
    @Column(name = "REVIEW_UPDATEDTIME")

    private Date reviewUpdateTime;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserVO userVO;
    @ManyToOne
    @JoinColumn(name = "PLACE_ID")
    private PlaceVO placeVO;


    public void updateContent(String content) {
        this.content = content;
    }




}
