package com.multitap.feedbackquery.presentation;

import com.multitap.feedbackquery.application.FeedbackRecordService;
import com.multitap.feedbackquery.common.response.BaseResponse;
import com.multitap.feedbackquery.dto.in.FeedbackRecordRequestDto;
import com.multitap.feedbackquery.dto.out.FeedbackRecordResponseDto;
import com.multitap.feedbackquery.vo.in.FeedbackRecordRequestVo;
import com.multitap.feedbackquery.vo.out.FeedbackRecordResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "피드백 조회 API", description = "피드백, AI 피드백 조회 관련 API endpoints")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/feedback-record")
public class FeedbackRecordController {

    private final FeedbackRecordService feedbackRecordService;

    @Operation(summary = "피드백 점수 조회", description = "카테고리별 피드백 점수를 조회합니다.")
    @GetMapping("/feedback-score")
    public BaseResponse<List<FeedbackRecordResponseVo>> getFeedbackScore(@RequestBody FeedbackRecordRequestVo feedbackRecordRequestVo) {
        List<FeedbackRecordResponseVo> feedbackRecordResponseVoList = feedbackRecordService.getFeedbackScore(FeedbackRecordRequestDto.from(feedbackRecordRequestVo))
                .stream()
                .map(FeedbackRecordResponseDto::toVo)
                .toList();

        log.info("FeedbackScore:{}", feedbackRecordResponseVoList.get(0).getCategoryCode());

        return new BaseResponse<>(feedbackRecordResponseVoList);
    }
}