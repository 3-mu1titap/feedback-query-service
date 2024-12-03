package com.multitap.feedbackquery.vo.out;

import com.multitap.feedbackquery.dto.out.FeedbackContentDto;
import com.multitap.feedbackquery.dto.out.FeedbackFirstLastScoreDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedbackContentResponseVo {

    private FeedbackFirstLastScoreDto feedbackFirstLastScoreDto;
    private String feedbackContentDto;

    @Builder
    public FeedbackContentResponseVo(FeedbackFirstLastScoreDto feedbackFirstLastScoreDto, String feedbackContentDto) {
        this.feedbackFirstLastScoreDto = feedbackFirstLastScoreDto;
        this.feedbackContentDto = feedbackContentDto;
    }
}
