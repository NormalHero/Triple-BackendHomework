package com.avery.triplebackendhomework.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EventDTO {

    public static final String ADD = "ADD";
    public static final String MOD = "MOD";
    public static final String DELETE  = "DELETE";

    private String type;
    private String action;
    private String reviewId;
    private String content;
    private List<String> attachedPhotoIds;
    private String userId;
    private String placeId;

}
