package com.chat.app.chatgroup;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatGroupRepository extends MongoRepository<ChatGroup,Integer> {
   Optional<ChatGroup> findById(Integer chatId);

   Optional<List<ChatGroup>> findByMemberIdsContains(int userId);
}
