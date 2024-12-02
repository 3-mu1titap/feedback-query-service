package com.multitap.feedbackquery.kafka.consumer;

import com.multitap.feedbackquery.dto.in.FeedbackScoreContentDto;
import com.multitap.feedbackquery.dto.in.FeedbackScoreRequestDto;
import com.multitap.feedbackquery.kafka.consumer.messagein.FeedbackContentResponseVo;
import com.multitap.feedbackquery.kafka.consumer.messagein.FeedbackScoreDto;
import com.multitap.feedbackquery.vo.out.FeedbackRecordResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class KafkaConsumer {

    private final KafkaConsumerService kafkaConsumerService;

    @KafkaListener(topics = "create-feedback-score-topic", groupId = "feedback-consumer-group", containerFactory = "feedbackScoreDtoListener")
    public void processFeedbackScore(FeedbackScoreDto feedbackScoreDto) {
        log.info("Received feedbackScoreDto :{}", feedbackScoreDto.getUuid());
        kafkaConsumerService.addFeedbackScore(FeedbackScoreRequestDto.from(feedbackScoreDto), feedbackScoreDto.getUuid());
    }

    @KafkaListener(topics = "create-feedback-record-gpt-topic", groupId = "feedback-consumer-group", containerFactory = "feedbackContentResponseVoListener")
    public void processFeedbackRecord(FeedbackContentResponseVo feedbackContentResponseVo) {
        log.info("Received feedbackContentResponseVo :{}", feedbackContentResponseVo.getUuid());
        kafkaConsumerService.addFeedbackContent(FeedbackScoreContentDto.from(feedbackContentResponseVo));

    }


}
