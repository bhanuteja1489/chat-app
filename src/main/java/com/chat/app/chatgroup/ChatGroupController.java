package com.chat.app.chatgroup;

import com.chat.app.message.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ChatGroupController {
    private  final ChatGroupService chatGroupService;
    private  int messageCount;
    private  int groupCount;


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/{chatId}")
    public void processMessage(@DestinationVariable String chatId, @Payload Message message) {
        message.setMessageId(++messageCount);
        chatGroupService.saveMessage(message);
        messagingTemplate.convertAndSend("/topic/chat/" + chatId, message);
    }
   @PostMapping("/chat/create")
    public ResponseEntity<Integer> createGroup(@RequestBody ChatGroup chatGroup)
   {
       chatGroup.setChatId(++groupCount);
       chatGroupService.addGroup(chatGroup);
       return ResponseEntity.ok(groupCount);
   }

   @GetMapping("/chat/messages/{chatId}")
    public ResponseEntity<List<Message>> fetchMessagesForChatGroup(@PathVariable int chatId)
   {
       Optional<List<Message>> msgL = chatGroupService.fetchMessagesForChatGroup(chatId);
       List<Message> msgList = msgL.get();
       return new ResponseEntity<>(msgList, HttpStatus.OK);
   }

    @GetMapping("/chat/user/{userId}") //fetches chat groups user is part of
    public ResponseEntity<List<ChatGroup>> fetchChatGroupsForUser(@PathVariable int userId) {
        Optional<List<ChatGroup>> chatGroupOpt = chatGroupService.fetchChatGroupsForUser(userId);

        if (chatGroupOpt.isPresent()) {
            return ResponseEntity.ok(chatGroupOpt.get());
        } else {
            return ResponseEntity.noContent().build(); // or .notFound().build() if preferred
        }
    }

}
