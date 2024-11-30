package com.multitap.feedbackquery.vo.out;

import com.multitap.feedbackquery.dto.out.ElementScore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedbackFirstLastScoreResponseVo {

    private ElementScore firstScore;
    private ElementScore lastScore;

    @Builder
    public FeedbackFirstLastScoreResponseVo(ElementScore firstScore, ElementScore lastScore) {
        this.firstScore = firstScore;
        this.lastScore = lastScore;
    }
}
