package com.multitap.feedbackquery.kafka.producer;

import com.multitap.feedbackquery.dto.out.FeedbackFirstLastScoreDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendCreateFeedbackScore(AiFeedbackScoreDto aiFeedbackScoreDto) {
        try {
            log.info("@@@uuid@@@: {}", aiFeedbackScoreDto.getUuid());
            kafkaTemplate.send("create-ai-feedback-score-topic", aiFeedbackScoreDto);
        } catch (Exception e) {
            log.info("create ai feedback score event send 실패 : " + e);
            throw new RuntimeException(e);
        }
    }
}
