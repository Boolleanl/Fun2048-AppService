package com.boollean.service.impl;

import com.boollean.dao.MessageDao;
import com.boollean.entity.MessageEntity;
import com.boollean.service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {
    private MessageDao messageDao;

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public List<MessageEntity> getAllMessages() {
        return this.messageDao.getAllMessages();
    }

    @Override
    public List<MessageEntity> getLatest100Messages() {
        return this.messageDao.getLatest100Messages();
    }

    @Override
    public List<MessageEntity> getLatest1000Messages() {
        return this.messageDao.getLatest1000Messages();
    }

    @Override
    public boolean addMessage(MessageEntity messageEntity) {
        return this.messageDao.addMessage(messageEntity);
    }
}
