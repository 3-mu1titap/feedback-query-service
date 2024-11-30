package com.multitap.feedbackquery.entity;

import com.multitap.feedbackquery.dto.in.FeedbackScoreRequestDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@Builder
@CompoundIndex(name = "user_category_index", def = "{'id': 1, 'feedbackScores.categoryCode': 1}")
public class FeedbackRecord {

    @Id
    private String id;
    private List<FeedbackScoreRequestDto> feedbackScore;
    //todo: 볼팡이 응답 값 카테고리 별로 가져와야 하니까
    //객체 만들어야 하는데 카테고리 넣고 응답값 넣어서 카테고리별 응답 값 받도록
    //시발 그럴라면 uuid 랑 카테고리 가지고 있어야 했네


}
