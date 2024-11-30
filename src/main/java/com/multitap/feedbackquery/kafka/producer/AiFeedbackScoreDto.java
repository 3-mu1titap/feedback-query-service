package com.multitap.feedbackquery.kafka.producer;

import com.multitap.feedbackquery.dto.out.FeedbackFirstLastScoreDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AiFeedbackScoreDto {

    private FeedbackFirstLastScoreDto.ElementScore firstScore;
    private FeedbackFirstLastScoreDto.ElementScore lastScore;
    private String categoryCode;

    @Builder
    public AiFeedbackScoreDto(com.multitap.feedbackquery.dto.out.FeedbackFirstLastScoreDto.ElementScore firstScore, com.multitap.feedbackquery.dto.out.FeedbackFirstLastScoreDto.ElementScore lastScore, String categoryCode) {
        this.firstScore = firstScore;
        this.lastScore = lastScore;
        this.categoryCode = categoryCode;
    }

    public static AiFeedbackScoreDto from(FeedbackFirstLastScoreDto feedbackFirstLastScoreDto, String categoryCode) {
        return AiFeedbackScoreDto.builder()
                .firstScore(feedbackFirstLastScoreDto.getFirstScore())
                .lastScore(feedbackFirstLastScoreDto.getLastScore())
                .categoryCode(categoryCode)
                .build();
    }
}
