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

/**
 * @author Boollean
 */
@Controller("messageAction")
@Scope("prototype")
public class MessageAction extends ActionSupport {

    @Resource
    private MessageService messageService;

    //用于生成JSON数据的HashMap
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

    /**
     * 重置JsonData里的数据，默认请求成功
     */
    public void reSetJsonData() {
        jsonData.clear();
        jsonData.put("code", 200);
        jsonData.put("msg", "success");
    }

    /**
     * 获取所有留言信息
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String getAllMessages() {
        List<MessageEntity> list = messageService.getAllMessages();
        reSetJsonData();
        jsonData.put("subjects", list);
        return "success";
    }

    /**
     * 获取最近的100条留言信息
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String getLatest100Messages() {
        List<MessageEntity> list = messageService.getLatest100Messages();
        reSetJsonData();
        jsonData.put("subjects", list);
        return "success";
    }

    /**
     * 获取最近的200条留言信息
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String getLatest200Messages() {
        List<MessageEntity> list = messageService.getLatest200Messages();
        reSetJsonData();
        jsonData.put("subjects", list);
        return "success";
    }

    /**
     * 添加一条新的留言
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String addMessage() {
        jsonData.clear();
        jsonData.put("code", 200);
        try {
            if (messageService.addMessage()) {
                //如果加入成功，则返回信息success
                jsonData.put("msg", "success");
            } else {
                //加入不成功则返回信息fail
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
