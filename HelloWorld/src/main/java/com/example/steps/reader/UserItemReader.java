package com.example.steps.reader;

import java.util.Collections;

import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.data.domain.Sort;

import com.example.entity.User;
import com.example.repository.UserRepository;

public class UserItemReader extends RepositoryItemReader<User> {

    public UserItemReader(UserRepository userRepository) {
        this.setRepository(userRepository);
        this.setMethodName("findAll");
        this.setPageSize(10);
        this.setSort(Collections.singletonMap("id", Sort.Direction.ASC));
        // this.setSaveState(false); // Uncomment if needed
    }
}
