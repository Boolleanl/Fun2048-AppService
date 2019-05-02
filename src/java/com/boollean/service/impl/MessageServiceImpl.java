package com.boollean.service.impl;

import com.boollean.Utils.GetRequestBodyUtils;
import com.boollean.dao.MessageDao;
import com.boollean.dao.impl.MessageDaoImpl;
import com.boollean.entity.MessageEntity;
import com.boollean.service.MessageService;
import com.google.gson.*;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Boollean
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    //获取日志记录器Logger，名字为本类类名
    private static Logger logger = LogManager.getLogger(MessageServiceImpl.class);

    @Resource
    private MessageDao messageDao;

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public List<MessageEntity> getAllMessages(){
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
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
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

        Gson gson = new GsonBuilder().registerTypeAdapter(Timestamp.class, new TimestampTypeAdapter()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        MessageEntity messageEntity = gson.fromJson(jsonObject, MessageEntity.class);
        return this.messageDao.addMessage(messageEntity);
    }

    /**
     * 时间类型转换类，用于将输入的时间转换为数据库可识别的时间格式
     */
    private class TimestampTypeAdapter implements JsonSerializer<Timestamp>, JsonDeserializer<Timestamp> {
        private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        public JsonElement serialize(Timestamp src, Type arg1, JsonSerializationContext arg2) {
            String dateFormatAsString = format.format(new Date(src.getTime()));
            return new JsonPrimitive(dateFormatAsString);
        }

        public Timestamp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (!(json instanceof JsonPrimitive)) {
                throw new JsonParseException("date必须输入String类型");
            }

            try {
                Date date = format.parse(json.getAsString());
                return new Timestamp(date.getTime());
            } catch (ParseException e) {
                throw new JsonParseException(e);
            }
        }
    }
}
