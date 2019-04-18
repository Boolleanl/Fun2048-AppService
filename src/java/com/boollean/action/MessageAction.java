package com.boollean.action;

import com.boollean.entity.MessageEntity;
import com.boollean.service.MessageService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("messageAction")
@Scope("prototype")
public class MessageAction extends ActionSupport {

    @Resource
    private MessageService messageService;

    private Map<String, Object> jsonData = new HashMap<String, Object>();

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

    public void reSetJsonData() {
        jsonData.clear();
        jsonData.put("code", 200);
        jsonData.put("msg", "success");
    }

    public String getAllMessages() {
        List<MessageEntity> list = messageService.getAllMessages();
        reSetJsonData();
        jsonData.put("subjects", list);
        return "success";
    }

    public String getLatest100Messages() {
        List<MessageEntity> list = messageService.getLatest100Messages();
        reSetJsonData();
        jsonData.put("subjects", list);
        return "success";
    }

    public String getLatest200Messages() {
        List<MessageEntity> list = messageService.getLatest200Messages();
        reSetJsonData();
        jsonData.put("subjects", list);
        return "success";
    }

    public String addMessage() {
        jsonData.clear();
        jsonData.put("code", 200);
        try {
            if (messageService.addMessage()) {
                jsonData.put("msg", "success");
            } else {
                jsonData.put("msg", "fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonData.put("msg", "fail");
        } finally {
            return "success";
        }
    }
}
