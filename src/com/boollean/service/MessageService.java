package com.boollean.service;

import com.boollean.entity.MessageEntity;

import java.util.List;

public interface MessageService {

    List<MessageEntity> getAllMessages();

    List<MessageEntity> getLatest100Messages();

    List<MessageEntity> getLatest1000Messages();

    boolean addMessage(MessageEntity messageEntity);
}
