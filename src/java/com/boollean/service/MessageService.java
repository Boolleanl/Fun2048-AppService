package com.boollean.service;

import com.boollean.entity.MessageEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 留言相关服务的接口
 *
 * @author Boollean
 */
@Service("messageService")
public interface MessageService {

    /**
     * 获取所有留言信息
     *
     * @return 包含所有留言信息的List
     */
    List<MessageEntity> getAllMessages();

    /**
     * 获取最近100条留言信息
     *
     * @return 包含最近100条留言信息的List
     */
    List<MessageEntity> getLatest100Messages();

    /**
     * 获取最近200条留言信息
     *
     * @return 包含最近200条留言信息的List
     */
    List<MessageEntity> getLatest200Messages();

    /**
     * 添加一条留言
     *
     * @return 添加是否成功
     */
    boolean addMessage();
}
