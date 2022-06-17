package com.avery.triplebackendhomework.service;

import com.avery.triplebackendhomework.domain.dto.EventDTO;
import com.avery.triplebackendhomework.domain.repository.*;
import com.avery.triplebackendhomework.domain.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    public static final int ADD_POINT  = 1;
    public static final int REMOVE_POINT  = -1;

    private final ReviewRepository reviewRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final AttachedPhotoRepository attachedPhotoRepository;


    public boolean isWriteReview(String userId, String placeId){
        return reviewRepository.getWriteReviewCount(userId,placeId) == 1;
    }

    @Transactional
    public void addReview(EventDTO event){
        PlaceVO placeVO =  placeRepository.findById(event.getPlaceId()).orElseThrow(() ->new IllegalArgumentException("장소가 존재하지 않습니다") );
        UserVO userVO = userRepository.findById(event.getUserId()).orElseThrow(() ->new IllegalArgumentException("사용자가 존재하지 않습니다.") );

        ReviewVO reviewVO = ReviewVO.builder().reviewId(event.getReviewId()).placeVO(placeVO).userVO(userVO).content(event.getContent()).build();
        reviewRepository.save(reviewVO);
        // 내용 확인
        if(Objects.isNull(event.getContent())){log.info("등록된 내용이 없습니다.");
        }else{ addPointHistory(event,"Content",ADD_POINT); }
        // 사진 확인
        if(Objects.isNull(event.getAttachedPhotoIds())){ log.info("등록된 사진이 없습니다.");
        }else{
            event.getAttachedPhotoIds().forEach(e->attachedPhotoRepository.save(AttachedPhotoVO.builder().attachedPhotoIDS(e).reviewVO(reviewVO).userVO(userVO).build()));
            addPointHistory(event,"Photo",ADD_POINT);
        }
        // 리뷰 등록
        if(reviewRepository.countByPlaceVO_PlaceId(event.getPlaceId())-1 == 0){ addPointHistory(event,"Bonus",ADD_POINT); }

    }

    @Transactional
    public void addPointHistory(EventDTO event,String reason,int point){

        PointHistoryVO pointHistoryVO = PointHistoryVO.builder()
                .pointHistoryId(UUID.randomUUID().toString())
                .reviewId(event.getReviewId())
                .userId(event.getUserId())
                .pointHistoryReason(reason)
                .pointHistoryGrantPoint(ADD_POINT).build();
        pointHistoryRepository.save(pointHistoryVO);
    }

    @Transactional
    public void modifyReview(EventDTO event){
        ReviewVO reviewVO = reviewRepository.findById(event.getReviewId()).orElseThrow(() ->new IllegalArgumentException("리뷰가 존재하지 않습니다"));

        if(!Objects.isNull(event.getContent())){ reviewVO.updateContent(event.getContent());}
        List<AttachedPhotoVO> attachedPhotoVOList =  attachedPhotoRepository.findAllByReviewVO_ReviewIdAndUserVO_UserId(event.getReviewId(),event.getUserId());
        // 첨부파일이 존재했다면
        if(!Objects.isNull(attachedPhotoVOList)){
            attachedPhotoRepository.findAllByReviewVO_ReviewIdAndUserVO_UserId(event.getReviewId(),event.getUserId()).forEach(attachedPhotoRepository::delete);
            if(!Objects.isNull(event.getAttachedPhotoIds())){
                UserVO userVO = userRepository.findById(event.getUserId()).orElseThrow(() ->new IllegalArgumentException("사용자가 존재하지 않습니다"));
                event.getAttachedPhotoIds().forEach(e->attachedPhotoRepository.save(AttachedPhotoVO.builder().attachedPhotoIDS(e).reviewVO(reviewVO).userVO(userVO).build()));
            }else {
                addPointHistory(event,"Remove-Photo",REMOVE_POINT);
            }
        }else {
            if(!Objects.isNull(event.getAttachedPhotoIds())){
                UserVO userVO = userRepository.findById(event.getUserId()).orElseThrow(() ->new IllegalArgumentException("사용자가 존재하지 않습니다"));
                event.getAttachedPhotoIds().forEach(e->attachedPhotoRepository.save(AttachedPhotoVO.builder().attachedPhotoIDS(e).reviewVO(reviewVO).userVO(userVO).build()));
                addPointHistory(event,"Photo",ADD_POINT);
            }
        }

        reviewRepository.save(reviewVO);

    }

    @Transactional
    public void removeReview(EventDTO event){
        ReviewVO reviewVO = reviewRepository.findById(event.getReviewId()).orElseThrow(() ->new IllegalArgumentException("리뷰가 존재하지 않습니다"));
        attachedPhotoRepository.findAllByReviewVO_ReviewIdAndUserVO_UserId(event.getReviewId(),event.getUserId()).forEach(attachedPhotoRepository::delete);
        pointHistoryRepository.findAllByReviewIdAndUserId(event.getReviewId(),event.getUserId()).forEach(e->{
            PointHistoryVO pointHistoryVO = PointHistoryVO.builder().pointHistoryId(UUID.randomUUID().toString()).userId(e.getUserId()).pointHistoryGrantPoint(REMOVE_POINT).pointHistoryReason("Remove-"+e.getPointHistoryReason()).reviewId(e.getReviewId()).build();
            pointHistoryRepository.save(pointHistoryVO);
        });
        reviewRepository.delete(reviewVO);
    }

}
