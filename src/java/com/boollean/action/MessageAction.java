package com.boollean.action;

import com.boollean.Utils.GetRequestBodyUtils;
import com.boollean.entity.MessageEntity;
import com.boollean.service.MessageService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
        jsonData.put("messages", list);
        return "success";
    }

    public String getLatest200Messages() {
        List<MessageEntity> list = messageService.getLatest200Messages();
        reSetJsonData();
        jsonData.put("messages", list);
        return "success";
    }

    public String addMessage() {
        reSetJsonData();
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            String jsonString = GetRequestBodyUtils.getRequestJsonString(request);
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
            if (messageService.addMessage(messageEntity)) {
                jsonData.put("subject", "OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonData.put("subject", "FAIL");
        } finally {
            return "success";
        }
    }
}
