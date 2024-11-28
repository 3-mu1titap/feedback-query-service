package com.multitap.feedbackquery.kafka.consumer;

import com.multitap.feedbackquery.dto.in.FeedbackScoreRequestDto;
import com.multitap.feedbackquery.entity.FeedbackRecord;
import com.multitap.feedbackquery.infrastructure.FeedbackRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final FeedbackRecordRepository feedbackRecordRepository;

    @Override
    public void addFeedbackScore(FeedbackScoreRequestDto feedbackScoreRequestDto, String uuid) {

        Optional<FeedbackRecord> existingFeedback = feedbackRecordRepository.findById(uuid);

        if (existingFeedback.isPresent()) {
            FeedbackRecord updatedFeedback = feedbackScoreRequestDto.updateToEntity(feedbackScoreRequestDto, existingFeedback.get());
            feedbackRecordRepository.save(updatedFeedback);
        } else {
            feedbackRecordRepository.save(feedbackScoreRequestDto.toEntity(feedbackScoreRequestDto, uuid));
        }
    }
}
