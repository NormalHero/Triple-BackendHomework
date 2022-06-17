package com.avery.triplebackendhomework.controller;


import com.avery.triplebackendhomework.domain.dto.EventDTO;
import com.avery.triplebackendhomework.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/events")
    public ResponseEntity events(@RequestBody EventDTO event){
        try {
            switch (event.getAction()){
                case EventDTO.ADD:
                    if(reviewService.isWriteReview(event.getUserId(),event.getPlaceId())){
                        return ResponseEntity.ok("이미 리뷰를 작성하셨습니다.");
                    }else{
                        reviewService.addReview(event);
                        return ResponseEntity.ok("리뷰를 성공적으로 작성하였습니다.");
                    }
                case EventDTO.MOD:
                    reviewService.modifyReview(event);
                    return ResponseEntity.ok("리뷰를 수정하였습니다.");
                case EventDTO.DELETE:
                    reviewService.removeReview(event);
                    return ResponseEntity.ok("리뷰를 삭제하였습니다.");
            }
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
        return ResponseEntity.badRequest().body("알 수 없는 오류가 발생하였습니다");
    }

   @ExceptionHandler(NullPointerException.class)
    public void exceptionHandler(NullPointerException e) {
        log.info(e.getMessage());
    }

}
