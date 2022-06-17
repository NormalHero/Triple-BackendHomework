package com.avery.triplebackendhomework.domain.repository;

import com.avery.triplebackendhomework.domain.vo.PointHistoryVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointHistoryRepository extends JpaRepository<PointHistoryVO,String> {
        List<PointHistoryVO> findAllByReviewIdAndUserId(String reviewId, String userId);
}
