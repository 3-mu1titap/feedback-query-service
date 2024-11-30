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
        //todo: 새로운 값이 저장되니까 이때 이전값 이후 값 추출하는 리포지토리 메소드 불러서 카테고리랑 값 ai 피드백 서비스로 넘기면
        // 피드백 서비스에서 카테고리에 맞는 프롬프트 찾아가지고 그 프롬프트랑 값이랑 gpt 넘겨주는 로직 어뎁터든 뭐든 서비스로 넘겨받는 ㅔapi 에서
        //응답 값 만든다음에 그걸 다시 카프카로 피드백 리드 서비스에 저장시키면 될듯?
    }
}
