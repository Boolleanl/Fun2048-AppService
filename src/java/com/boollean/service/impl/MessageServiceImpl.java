package com.boollean.service.impl;

import com.boollean.Utils.GetRequestBodyUtils;
import com.boollean.dao.MessageDao;
import com.boollean.entity.MessageEntity;
import com.boollean.service.MessageService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    public boolean addMessage() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String jsonString = null;
        try {
            jsonString = GetRequestBodyUtils.getRequestJsonString(request);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        //获得解析者
        JsonParser jsonParser = new JsonParser();
        //获得根节点元素
        JsonElement root = jsonParser.parse(jsonString);
        //根据文档判断根节点属于什么类型的Gson节点对象
        JsonObject element = root.getAsJsonObject();
        //取得节点下的某个节点的value

        JsonObject jsonObject = element.getAsJsonObject("subject");
        Gson gson = new Gson();
        MessageEntity messageEntity = gson.fromJson(jsonObject, MessageEntity.class);
        return this.messageDao.addMessage(messageEntity);
    }
}
