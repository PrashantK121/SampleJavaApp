package com.example.steps.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.User;
import com.example.producer.EventProducer;
import com.example.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserItemWriter implements ItemWriter<User> {

	//@Autowired public EventProducer eventProducer;

	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional
	public void write(Chunk<? extends User> users) throws Exception {
		List<User> userList = users.getItems().stream().map(user -> {
			System.out.println("Entered for processing : "+user.getId());
			//eventProducer.sendKafkaMessage(user.getFirstName());
			user.setFirstName(user.getFirstName().toUpperCase() + "1");
			//user.setId(user.getId()+10);
			return user;
		}).collect(Collectors.toList());
		userList.forEach(user->System.out.println(user.getFirstName()));
		userRepository.saveAllAndFlush(userList);
	}
}
