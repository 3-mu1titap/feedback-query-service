package com.multitap.feedbackquery.dto.out;

import com.multitap.feedbackquery.vo.out.FeedbackFirstLastScoreResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedbackFirstLastScoreDto {

    private ElementScore firstScore;
    private ElementScore lastScore;

    @Builder
    public FeedbackFirstLastScoreDto(ElementScore firstScore, ElementScore lastScore) {
        this.firstScore = firstScore;
        this.lastScore = lastScore;
    }

    public static FeedbackFirstLastScoreResponseVo toVo(FeedbackFirstLastScoreDto dto) {
        return FeedbackFirstLastScoreResponseVo.builder()
                .firstScore(dto.getFirstScore())
                .lastScore(dto.getLastScore())
                .build();
    }
}
