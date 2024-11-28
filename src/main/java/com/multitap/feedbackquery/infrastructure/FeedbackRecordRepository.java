package com.multitap.feedbackquery.infrastructure;

import com.multitap.feedbackquery.dto.in.FeedbackScoreRequestDto;
import com.multitap.feedbackquery.entity.FeedbackRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRecordRepository extends MongoRepository<FeedbackRecord, String> {

    @Query(value = "{ 'id': ?0, 'feedbackScore.categoryCode': ?1 }",
            fields = "{ 'feedbackScore': { $elemMatch: { 'categoryCode': ?1 } } }")
    List<FeedbackScoreRequestDto> findAllFeedbackByIdAndCategoryCode(String uuid, String categoryCode);

}