package com.boollean.service.impl;

import com.boollean.dao.MessageDao;
import com.boollean.entity.MessageEntity;
import com.boollean.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Resource
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
    public List<MessageEntity> getLatest200Messages() {
        return this.messageDao.getLatest200Messages();
    }

    @Override
    public boolean addMessage(MessageEntity messageEntity) {
        return this.messageDao.addMessage(messageEntity);
    }
}
