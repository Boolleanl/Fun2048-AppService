package com.boollean.action;

import com.boollean.entity.MessageEntity;
import com.boollean.service.MessageService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MessageAction extends ActionSupport {

    HttpServletRequest request;
    HttpServletResponse response;
    MessageService messageService;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public String getAllMessages() {
        System.out.println("查询所有留言。");
        List<MessageEntity> list = messageService.getAllMessages();
        Gson gson = new Gson();
        String result = gson.toJson(list);
        System.out.println(result);
        return result;
    }

    public String getLatest100Messages() {
        System.out.println("查询新的100条留言。");
        List<MessageEntity> list = messageService.getLatest100Messages();
        Gson gson = new Gson();
        String result = gson.toJson(list);
        System.out.println(result);
        return result;
    }

    public String getLatest1000Messages() {
        System.out.println("查询新的1000条留言。");
        List<MessageEntity> list = messageService.getLatest1000Messages();
        Gson gson = new Gson();
        String result = gson.toJson(list);
        System.out.println(result);
        return result;
    }

    public String addMessage(String jsonString) {
        Gson gson = new Gson();
        MessageEntity messageEntity = gson.fromJson(jsonString, MessageEntity.class);
        if (messageService.addMessage(messageEntity)) {
            return "成功";
        }
        return "失败";
    }
}
