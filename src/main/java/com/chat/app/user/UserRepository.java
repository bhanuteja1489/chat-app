package com.chat.app.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,Integer> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
