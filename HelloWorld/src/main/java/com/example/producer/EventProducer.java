package com.example.producer;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component

public class EventProducer {

	@Autowired
	KafkaTemplate<Integer, String> kafkaTemplate;
	ObjectMapper objectMapper;
	
	@Value("${spring.kafka.topic.test}")
	public String topicTest;
	
	public void sendKafkaMessage(String msg) {
		List<Header> headers = new ArrayList<>(2);
		headers.add(new RecordHeader("headerKey", "headerValue".getBytes()));
		ProducerRecord<Integer, String> msgData = new ProducerRecord<Integer, String>(topicTest, null, 1, msg, headers);
		kafkaTemplate.send(msgData);
		System.out.println("Message sent successfully!");
	}
}
