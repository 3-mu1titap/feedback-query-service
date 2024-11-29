package com.multitap.feedbackquery.infrastructure;

import com.multitap.feedbackquery.entity.FeedbackRecord;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FeedbackRecordRepository extends MongoRepository<FeedbackRecord, String> {

    @Query("{ 'id': ?0, 'feedbackScore.categoryCode': ?1 }")
    @Aggregation(pipeline = {
            "{ $match: { 'id': ?0 } }",
            "{ $unwind: '$feedbackScore' }",
            "{ $match: { 'feedbackScore.categoryCode': ?1 } }",
            "{ $sort: { 'feedbackScore.mentoringDate': -1 } }",
            "{ $group: { " +
                    "'_id': '$_id', " +
                    "'feedbackScore': { $push: '$feedbackScore' }" +
                    "} }"
    })
    Optional<FeedbackRecord> findByIdAndCategoryCodeOrderByMentoringDateDesc(String id, String categoryCode);
}

