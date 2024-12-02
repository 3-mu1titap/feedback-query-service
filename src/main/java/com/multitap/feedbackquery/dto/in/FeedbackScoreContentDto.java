package com.multitap.feedbackquery.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedbackScoreContentDto {

    private String categoryCode;
    private Object content;

    @Builder
    public FeedbackScoreContentDto(String categoryCode, Object content) {
        this.categoryCode = categoryCode;
        this.content = content;
    }

}
