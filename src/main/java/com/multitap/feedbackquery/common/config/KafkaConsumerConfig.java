package com.multitap.feedbackquery.common.config;

import com.multitap.feedbackquery.kafka.consumer.messagein.FeedbackScoreDto;
import com.multitap.feedbackquery.vo.out.FeedbackRecordResponseVo;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, FeedbackScoreDto> feedbackScoreConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092,localhost:39092,localhost:49092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "feedback-consumer-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(FeedbackScoreDto.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedbackScoreDto> feedbackScoreDtoListener() {
        ConcurrentKafkaListenerContainerFactory<String, FeedbackScoreDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(feedbackScoreConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, FeedbackRecordResponseVo> feedbackRecordConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092,localhost:39092,localhost:49092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "feedback-consumer-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(FeedbackRecordResponseVo.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FeedbackRecordResponseVo> feedbackRecordResponseVoListener() {
        ConcurrentKafkaListenerContainerFactory<String, FeedbackRecordResponseVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(feedbackRecordConsumerFactory());
        return factory;
    }
}
