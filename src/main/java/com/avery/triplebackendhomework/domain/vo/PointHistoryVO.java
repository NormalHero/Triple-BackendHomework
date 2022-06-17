package com.avery.triplebackendhomework.domain.vo;


import com.avery.triplebackendhomework.domain.dto.EventDTO;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TRIPLE_POINTHISTORY")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@ToString(of = {"pointHistoryId","pointHistoryGrantPoint","pointHistoryCreatedTime","pointHistoryReason"})
public class PointHistoryVO {

    @Id
    @Column(name = "POINTHISTORY_ID")
    private String pointHistoryId;
    @Column(name = "POINTHISTORY_GRANTPOINT")
    private int pointHistoryGrantPoint;
    @Generated(GenerationTime.INSERT)
    @Column(name = "POINTHISTORY_CREATEDTIME")
    private Date pointHistoryCreatedTime;
    @Column(name = "POINTHISTORY_REASON")
    private String pointHistoryReason;
    @Column(name = "REVIEW_ID")
    private String reviewId;
    @Column(name = "USER_ID")
    private String userId;



}
