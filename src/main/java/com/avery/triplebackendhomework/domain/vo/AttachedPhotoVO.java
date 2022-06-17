package com.avery.triplebackendhomework.domain.vo;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TRIPLE_ATTACHEDPHOTO")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"attachedPhotoIDS"})
public class AttachedPhotoVO {

   @Id
   @Column(name = "ATTACHEDPHOTO_IDS")
   private String attachedPhotoIDS;
   @ManyToOne
   @JoinColumn(name = "REVIEW_ID")
   private ReviewVO reviewVO;
   @ManyToOne
   @JoinColumn(name = "USER_ID")
   private UserVO userVO;

}

