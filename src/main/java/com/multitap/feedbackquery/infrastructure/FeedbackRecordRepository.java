package com.multitap.feedbackquery.infrastructure;

import com.multitap.feedbackquery.dto.out.FeedbackFirstLastScoreDto;
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


    @Aggregation(pipeline = {
            "{ $match: { _id: ?0, 'feedbackScore.categoryCode': ?1 } }",
            "{ $project: { feedbackScore: { $filter: { " +
                    "input: '$feedbackScore', " +
                    "as: 'score', " +
                    "cond: { $eq: ['$$score.categoryCode', ?1] } " +
                    "} } } }",
            "{ $project: { " +
                    "firstScore: { " +
                    "element1: { $arrayElemAt: ['$feedbackScore.element1', 0] }, " +
                    "element2: { $arrayElemAt: ['$feedbackScore.element2', 0] }, " +
                    "element3: { $arrayElemAt: ['$feedbackScore.element3', 0] }, " +
                    "element4: { $arrayElemAt: ['$feedbackScore.element4', 0] }, " +
                    "element5: { $arrayElemAt: ['$feedbackScore.element5', 0] } " +
                    "}, " +
                    "lastScore: { " +
                    "element1: { $arrayElemAt: ['$feedbackScore.element1', -1] }, " +
                    "element2: { $arrayElemAt: ['$feedbackScore.element2', -1] }, " +
                    "element3: { $arrayElemAt: ['$feedbackScore.element3', -1] }, " +
                    "element4: { $arrayElemAt: ['$feedbackScore.element4', -1] }, " +
                    "element5: { $arrayElemAt: ['$feedbackScore.element5', -1] } " +
                    "} " +
                    "} }"
    })
    FeedbackFirstLastScoreDto findFirstAndLastFeedbackScore(String id, String categoryCode);
}



