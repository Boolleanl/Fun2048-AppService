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

    private Map<String,Object> jsonData = new HashMap<String,Object>();

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

//    public String addMessage() {
//        jsonData.clear();
//        request = ServletActionContext.getRequest();
//        try {
//            String jsonString = request.getQueryString();
//            Gson gson = new Gson();
//            MessageEntity messageEntity = gson.fromJson(jsonString, MessageEntity.class);
//            if (messageService.addMessage(messageEntity)) {
//                jsonData.put("success",true);
//                return "success";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        jsonData.put("success",false);
//        return "fail";
//    }
}
