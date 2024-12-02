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

    public FeedbackRecord toEntity(FeedbackScoreContentDto feedbackScoreContentDto, FeedbackRecord existedFeedbackRecord) {
        FeedbackRecord feedbackRecord = FeedbackRecord.builder()
                .id(existedFeedbackRecord.getId())
                .feedbackScore(existedFeedbackRecord.getFeedbackScore())
                .feedbackContent(new ArrayList<>())
                .build();
        feedbackRecord.getFeedbackContent().add(feedbackScoreContentDto);
        return feedbackRecord;

    }

    public FeedbackRecord updateToEntity(FeedbackScoreContentDto feedbackScoreContentDto,  FeedbackRecord feedbackRecord) {
        // 기존 feedbackContent 리스트에 새로운 피드백 추가
        feedbackRecord.getFeedbackContent().add(feedbackScoreContentDto);

        // 변경된 FeedbackRecord 반환
        return FeedbackRecord.builder()
                .id(feedbackRecord.getId())
                .feedbackScore(feedbackRecord.getFeedbackScore())
                .feedbackContent().add(feedbackScoreContentDto)
                .build();
    }

}
