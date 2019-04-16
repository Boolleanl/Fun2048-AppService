package com.boollean.service;

import com.boollean.entity.MessageEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
public interface MessageService {

    List<MessageEntity> getAllMessages();

    List<MessageEntity> getLatest100Messages();

    List<MessageEntity> getLatest200Messages();

    boolean addMessage();
}
