package com.chat.app.chatgroup;

import com.chat.app.message.Message;
import com.chat.app.message.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatGroupService {
    private final ChatGroupRepository chatGroupRepository;
    private final MessageRepository messageRepository;

    public boolean addMemberToGroup(Integer groupId, List<Integer>newMemberIds)
    {
        Optional<ChatGroup> group = chatGroupRepository.findById(groupId);

        if(group.isPresent())
        {
           ChatGroup grpObj = group.get();
           if(grpObj.addMembers(newMemberIds))
           {
              chatGroupRepository.save(grpObj);
              return true;
           }
        }
        return false;
    }

    public boolean makeMemberAdmin(Integer groupId, Integer memberId)
    {
        Optional<ChatGroup> group = chatGroupRepository.findById(groupId);

        if(group.isPresent())
        {
            ChatGroup grpObj = group.get();
            if(grpObj.getMemberIds().contains(memberId))
            {
               if(!grpObj.getAdminIds().contains(memberId))
                   grpObj.getAdminIds().add(memberId);
               return true;
            }
        }
         return false;

    }

    public Optional<List<Message>> getMessagesForChatGroup(Integer groupId)
    {
        return messageRepository.findAllByChatId(groupId);
    }

    public void saveMessage(Message message)
    {
        messageRepository.save(message);
    }

    public void addGroup(ChatGroup chatGroup) {
        chatGroupRepository.save(chatGroup);
    }

    public Optional<List<Message>> fetchMessagesForChatGroup(int chatId) {
        return messageRepository.findAllByChatId(chatId);
    }

    public Optional<List<ChatGroup>> fetchChatGroupsForUser(int userId) {
        return chatGroupRepository.findByMemberIdsContains(userId);
    }
}
