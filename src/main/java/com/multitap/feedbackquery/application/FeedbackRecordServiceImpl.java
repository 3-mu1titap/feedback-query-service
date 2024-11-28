package com.multitap.feedbackquery.application;

import com.multitap.feedbackquery.dto.in.FeedbackRecordRequestDto;
import com.multitap.feedbackquery.dto.out.FeedbackRecordResponseDto;
import com.multitap.feedbackquery.infrastructure.FeedbackRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackRecordServiceImpl implements FeedbackRecordService {

    private final FeedbackRecordRepository feedbackRecordRepository;

    @Override
    public List<FeedbackRecordResponseDto> getFeedbackScore(FeedbackRecordRequestDto feedbackRecordRequestDto) {
        List<FeedbackRecordResponseDto> recordResponseDtos = feedbackRecordRepository.findAllFeedbackByIdAndCategoryCode(feedbackRecordRequestDto.getUuid(), feedbackRecordRequestDto.getCategoryCode())
                .stream()
                .map(FeedbackRecordResponseDto::from)
                .toList();

        log.info("dto:{}", recordResponseDtos.get(0).getCategoryCode());

        return recordResponseDtos;
    }
}
