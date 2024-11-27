package com.multitap.feedbackquery.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class FeedbackRecord {

    @Id
    private String id;




}
