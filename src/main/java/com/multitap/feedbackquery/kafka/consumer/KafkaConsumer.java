package com.multitap.feedbackquery.kafka.consumer;

import com.multitap.feedbackquery.dto.in.FeedbackScoreRequestDto;
import com.multitap.feedbackquery.kafka.consumer.messagein.FeedbackScoreDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class KafkaConsumer {

    private final KafkaConsumerService kafkaConsumerService;

    @KafkaListener(topics = "create-feedback-score-topic", groupId = "feedback-consumer-group", containerFactory = "memberDtoListener")
    public void processFeedbackScore(FeedbackScoreDto feedbackScoreDto) {
        log.info("Received feedbackScoreDto :{}", feedbackScoreDto.getUuid());
        kafkaConsumerService.addFeedbackScore(FeedbackScoreRequestDto.from(feedbackScoreDto), feedbackScoreDto.getUuid());
    }

}
