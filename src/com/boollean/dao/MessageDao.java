package com.boollean.dao;

import com.boollean.entity.MessageEntity;

import java.util.List;

public interface MessageDao {
    void init();

    List<MessageEntity> getAllMessages();

    List<MessageEntity> getLatest100Messages();

    List<MessageEntity> getLatest1000Messages();

    boolean addMessage(MessageEntity messageEntity);

    void close();
}
