package com.chat.app.chatgroup;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "groups")
public class ChatGroup {
    @Id
    private Integer       chatId;
    private String        groupName;
    private ChatType      type;
    private List<Integer> adminIds;
    private List<Integer> memberIds;

    public boolean addMembers(List<Integer>newMemberIds)
    {
        int maxMemberSize = 100;
        int currSize = memberIds.size();
        if(currSize + newMemberIds.size() > maxMemberSize)
            return false;
        else{
            memberIds.addAll(newMemberIds);
            return true;
        }
    }
}
