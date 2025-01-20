package com.example.steps.processor;

import com.example.entity.User;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class UserItemProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) throws Exception {
        // Process user entity
        user.setFirstName(user.getFirstName().toUpperCase());
        return user;
    }
}
