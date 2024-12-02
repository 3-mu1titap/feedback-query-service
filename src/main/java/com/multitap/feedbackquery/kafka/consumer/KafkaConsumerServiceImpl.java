package com.multitap.feedbackquery.kafka.consumer;

import com.multitap.feedbackquery.common.exception.BaseException;
import com.multitap.feedbackquery.common.response.BaseResponseStatus;
import com.multitap.feedbackquery.dto.in.FeedbackScoreContentDto;
import com.multitap.feedbackquery.dto.in.FeedbackScoreRequestDto;
import com.multitap.feedbackquery.entity.FeedbackRecord;
import com.multitap.feedbackquery.infrastructure.FeedbackRecordRepository;
import com.multitap.feedbackquery.kafka.producer.AiFeedbackScoreDto;
import com.multitap.feedbackquery.kafka.producer.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final FeedbackRecordRepository feedbackRecordRepository;
    private final KafkaProducerService kafkaProducerService;

    @Override
    public void addFeedbackScore(FeedbackScoreRequestDto feedbackScoreRequestDto, String uuid) {

        Optional<FeedbackRecord> existingFeedback = feedbackRecordRepository.findById(uuid);

        if (existingFeedback.isPresent()) {
            FeedbackRecord updatedFeedback = feedbackScoreRequestDto.updateToEntity(feedbackScoreRequestDto, existingFeedback.get());
            feedbackRecordRepository.save(updatedFeedback);
        } else {
            feedbackRecordRepository.save(feedbackScoreRequestDto.toEntity(feedbackScoreRequestDto, uuid));
        }

        // feedback score -> ai feedback-service
        kafkaProducerService.sendCreateFeedbackScore(AiFeedbackScoreDto.from(feedbackRecordRepository.findFirstAndLastFeedbackScore(uuid, feedbackScoreRequestDto.getCategoryCode()), feedbackScoreRequestDto.getCategoryCode()));
    }

    @Override
    public void addFeedbackContent(FeedbackScoreContentDto feedbackScoreContentDto) {

        feedbackRecordRepository.findFeedbackContentByIdAndCategory(feedbackScoreContentDto.getId(), feedbackScoreContentDto.getCategory())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_MEMBER_INFO));
    }
}