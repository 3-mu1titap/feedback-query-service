package com.multitap.feedbackquery.dto.in;

import com.multitap.feedbackquery.vo.in.FeedbackRecordRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedbackRecordRequestDto {

    private String uuid;
    private String categoryCode;

    @Builder
    public FeedbackRecordRequestDto(String uuid, String categoryCode) {
        this.uuid = uuid;
        this.categoryCode = categoryCode;
    }

    public static FeedbackRecordRequestDto from(FeedbackRecordRequestVo feedbackRecordRequestVo) {
        return FeedbackRecordRequestDto.builder()
                .uuid(feedbackRecordRequestVo.getUuid())
                .categoryCode(feedbackRecordRequestVo.getCategoryCode())
                .build();
    }

}
