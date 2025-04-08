package com.chat.app.message;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends MongoRepository<Message,Integer>{
    Optional<List<Message>> findAllByChatId(Integer chatId);
}
