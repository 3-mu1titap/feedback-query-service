package com.multitap.feedbackquery.dto.in;

import com.multitap.feedbackquery.entity.FeedbackRecord;
import com.multitap.feedbackquery.kafka.consumer.messagein.FeedbackContentResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class FeedbackScoreContentDto {

    private String id;
    private String category;
    private Object content;

    @Builder
    public FeedbackScoreContentDto(String id, String category, Object content) {
        this.id = id;
        this.category = category;
        this.content = content;
    }

    public static FeedbackScoreContentDto from(FeedbackContentResponseVo feedbackContentResponseVo) {
        return FeedbackScoreContentDto.builder()
                .id(feedbackContentResponseVo.getUuid())
                .category(feedbackContentResponseVo.getCategoryCode())
                .content(feedbackContentResponseVo.getContent())
                .build();
    }

    private void updateOrCreateFeedbackContent(FeedbackRecord feedbackRecord, FeedbackScoreContentDto feedbackScoreContentDto) {
        if (feedbackRecord.getFeedbackContent() == null) {
            feedbackRecord.setFeedbackContent(new ArrayList<>());
        }
        feedbackRecord.getFeedbackContent().add(feedbackScoreContentDto);
    }


    public FeedbackRecord toEntity(FeedbackScoreContentDto feedbackScoreContentDto, FeedbackRecord feedbackRecord) {
        updateOrCreateFeedbackContent(feedbackRecord, feedbackScoreContentDto);
        return feedbackRecord;
    }

}