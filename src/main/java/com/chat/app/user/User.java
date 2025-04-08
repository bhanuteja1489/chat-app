package com.chat.app.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private  Integer      userId;
    private  String       username;
    private  String       password;
}
