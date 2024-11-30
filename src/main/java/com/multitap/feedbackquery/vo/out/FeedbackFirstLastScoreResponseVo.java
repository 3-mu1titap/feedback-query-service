package com.multitap.feedbackquery.vo.out;

import com.multitap.feedbackquery.dto.out.FeedbackFirstLastScoreDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedbackFirstLastScoreResponseVo {

    private String id;
    private FeedbackFirstLastScoreDto.ElementScore firstScore;
    private FeedbackFirstLastScoreDto.ElementScore lastScore;

    @Builder
    public FeedbackFirstLastScoreResponseVo(String id, FeedbackFirstLastScoreDto.ElementScore firstScore, FeedbackFirstLastScoreDto.ElementScore lastScore) {
        this.id = id;
        this.firstScore = firstScore;
        this.lastScore = lastScore;
    }
}
