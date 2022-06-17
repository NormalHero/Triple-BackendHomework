package com.avery.triplebackendhomework.domain.vo;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TRIPLE_USER")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"userId"})
public class UserVO {

    @Id
    @Column( name = "USER_ID")
    private String userId;


}