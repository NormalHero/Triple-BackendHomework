package com.avery.triplebackendhomework.domain.repository;

import com.avery.triplebackendhomework.domain.vo.ReviewVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<ReviewVO,String> {

    int countByPlaceVO_PlaceId(String placeId);
    @Query(value = "select count(REVIEW_ID) from TRIPLE_REVIEW where PLACE_ID = :placeId and USER_ID = :userId", nativeQuery = true)
    int getWriteReviewCount(String userId,String placeId);
}
