package com.multitap.feedbackquery.dto.out;

import com.multitap.feedbackquery.vo.out.FeedbackContentResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedbackContentResponseDto {

    private FeedbackFirstLastScoreDto feedbackFirstLastScoreDto;
    private String feedbackContentDto;

    @Builder
    public FeedbackContentResponseDto(FeedbackFirstLastScoreDto feedbackFirstLastScoreDto, String feedbackContentDto) {
        this.feedbackFirstLastScoreDto = feedbackFirstLastScoreDto;
        this.feedbackContentDto = feedbackContentDto;
    }

    public FeedbackContentResponseVo toVo(FeedbackContentResponseDto feedbackContentResponseDto) {
        return FeedbackContentResponseVo.builder()
                .feedbackFirstLastScoreDto(feedbackFirstLastScoreDto)
                .feedbackContentDto(feedbackContentDto)
                .build();
    }
}
