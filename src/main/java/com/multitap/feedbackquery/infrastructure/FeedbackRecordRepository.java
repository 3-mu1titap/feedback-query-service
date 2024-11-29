package com.multitap.feedbackquery.infrastructure;
import com.multitap.feedbackquery.entity.FeedbackRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FeedbackRecordRepository extends MongoRepository<FeedbackRecord, String> {

    @Query(value = "{ 'id': ?0 }", fields = "{ 'feedbackScore': 1 }")
    Optional<FeedbackRecord> findFeedbackRecordById(String uuid);
    
}