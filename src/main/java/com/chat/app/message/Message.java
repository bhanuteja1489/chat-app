package com.chat.app.message;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Document(collection = "messages")
public class Message {
   @Id
   private Integer       messageId;
   private Integer       senderId;
   private Integer       chatId;      // represents (one-one|group) chat-id
   private MessageType   messageType; // TEXT | AUDIO | FILE | VIDEO
   private String        content;
   private Instant       timestamp;
   private List<Integer> seenBy;
}
