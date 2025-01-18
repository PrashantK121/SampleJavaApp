package com.example.steps.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.User;
import com.example.producer.EventProducer;

public class UserItemWriter implements ItemWriter<User> {

	@Autowired
	public EventProducer eventProducer;
	
	@Override
	public void write(Chunk<? extends User> users) throws Exception {
		users.forEach(user->{
			System.out.println("Entered for processing");
			eventProducer.sendKafkaMessage(user.getFirstName());
		});
		
	}
}
