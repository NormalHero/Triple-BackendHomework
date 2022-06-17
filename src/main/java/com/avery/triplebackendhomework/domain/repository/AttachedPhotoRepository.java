package com.avery.triplebackendhomework.domain.repository;

import com.avery.triplebackendhomework.domain.vo.AttachedPhotoVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachedPhotoRepository extends JpaRepository<AttachedPhotoVO,String> {
    List<AttachedPhotoVO> findAllByReviewVO_ReviewIdAndUserVO_UserId(String reviewId, String userId);
}
