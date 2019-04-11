package com.boollean.dao;

import com.boollean.entity.MessageEntity;

import java.util.List;

public interface MessageDao {
    List<MessageEntity> getAllMessages();

    List<MessageEntity> getLatest100Messages();

    List<MessageEntity> getLatest200Messages();

    boolean addMessage(MessageEntity messageEntity);
}
