package com.multitap.feedbackquery.kafka.consumer.messagein;

import com.multitap.feedbackquery.dto.in.FeedbackScoreRequestDto;
import com.multitap.feedbackquery.entity.FeedbackRecord;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class FeedbackContentResponseVo {

    private String uuid;
    private String categoryCode;
    private Object content;

    @Builder
    public FeedbackContentResponseVo(String uuid, String categoryCode, Object content) {
        this.uuid = uuid;
        this.categoryCode = categoryCode;
        this.content = content;
    }
}
