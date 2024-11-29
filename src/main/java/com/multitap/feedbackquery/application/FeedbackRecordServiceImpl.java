package com.multitap.feedbackquery.application;

import com.multitap.feedbackquery.dto.in.FeedbackRecordRequestDto;
import com.multitap.feedbackquery.dto.out.FeedbackRecordResponseDto;
import com.multitap.feedbackquery.entity.FeedbackRecord;
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

        return feedbackRecordRepository.findByIdAndCategoryCodeOrderByMentoringDateDesc(feedbackRecordRequestDto.getUuid(), feedbackRecordRequestDto.getCategoryCode())
                .map(FeedbackRecord::getFeedbackScore)
                .orElse(List.of())
                .stream()
                .map(FeedbackRecordResponseDto::from)
                .toList();
    }
}


