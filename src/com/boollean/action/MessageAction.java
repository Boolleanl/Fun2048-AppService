package com.boollean.action;

import com.boollean.entity.MessageEntity;
import com.boollean.entity.UserEntity;
import com.boollean.service.MessageService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageAction extends ActionSupport implements ServletRequestAware {

    HttpServletRequest request;
    @Autowired
    @Qualifier("messageService")
    MessageService messageService;

    private Map<String,Object> jsonData = new HashMap<String,Object>();

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    public String getAllMessages() {
        System.out.println("查询所有留言。");
        List<MessageEntity> list = messageService.getAllMessages();
        jsonData.clear();
        jsonData.put("success",true);
        jsonData.put("messages",list);
        return "success";
    }

    public String getLatest100Messages() {
        System.out.println("查询新的100条留言。");
        List<MessageEntity> list = messageService.getLatest100Messages();
        jsonData.clear();
        jsonData.put("success",true);
        jsonData.put("messages",list);
        return "success";
    }

    public String getLatest1000Messages() {
        System.out.println("查询新的1000条留言。");
        List<MessageEntity> list = messageService.getLatest1000Messages();
        jsonData.clear();
        jsonData.put("success",true);
        jsonData.put("messages",list);
        return "success";
    }

    public String addMessage() {
        jsonData.clear();
        request = ServletActionContext.getRequest();
        try {
            String jsonString = request.getQueryString();
            Gson gson = new Gson();
            MessageEntity messageEntity = gson.fromJson(jsonString, MessageEntity.class);
            if (messageService.addMessage(messageEntity)) {
                jsonData.put("success",true);
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonData.put("success",false);
        return "fail";
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }
}
