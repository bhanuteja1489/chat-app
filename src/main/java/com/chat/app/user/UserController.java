package com.chat.app.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private  final  UserService userService;
    private  int userCount = 0;


    @PostMapping("/user/register")
    public ResponseEntity<Map<String,Object>> registerUser(@RequestBody User user)
    {
       String username = user.getUsername();
       Map<String,Object> response = new HashMap<>();
       if(userService.checkIfUserExists(username))
       {
          response.put("status","failure");
          response.put("reason","username already taken");
          return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
       }
       else
       {
           int userId = ++userCount;
           user.setUserId(userId);
           userService.addUser(user);
           response.put("status","success");
           response.put("userId", userId);
           return new ResponseEntity<>(response, HttpStatus.OK);
       }
    }


    @GetMapping("/user/all")
    public ResponseEntity<Map<Integer, String>> fetchAllUsers() {
        List<User> users = userService.fetchAllUsers();
        Map<Integer, String> userMap = new HashMap<>();

        for (User user : users) {
            userMap.put(user.getUserId(), user.getUsername());
        }

        return ResponseEntity.ok(userMap);
    }


    @PostMapping("/user/login")
    public ResponseEntity<Map<String,Object>> loginUser(@RequestBody User user)
    {
        String username = user.getUsername();
        String password = user.getPassword();
        Map<String,Object> response = new HashMap<>();
        if(userService.checkIfUserExists(username))
        {
            Optional<User> usr = userService.getUserFromUserName(username);
            if(usr.isPresent())
            {
               User user1 = usr.get();
               if(user1.getPassword().equals(user.getPassword()))
               {
                  response.put("status","success");
                  response.put("userId",user1.getUserId());
                  return new ResponseEntity<>(response,HttpStatus.OK);
               }

            }
            response.put("status","failure");
            response.put("reason","password doesn't match");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        else
        {
            response.put("status","failure");
            response.put("reason", "username doesn't exist");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/user/mapping")
    public ResponseEntity<Map<String, Object>> getUserId2UserNameMapping() {
        List<User> users = userService.fetchAllUsers();
        Map<Integer, String> idToUsernameMap = new HashMap<>();

        for (User user : users) {
            idToUsernameMap.put(user.getUserId(), user.getUsername());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("userMap", idToUsernameMap);

        return ResponseEntity.ok(response);
    }
}
