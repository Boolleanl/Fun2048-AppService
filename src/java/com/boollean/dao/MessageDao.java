package com.boollean.dao;

import com.boollean.entity.MessageEntity;

import java.util.List;

/**
 * @author Boollean
 */
public interface MessageDao {

    /**
     * 查询所有留言信息
     * @return 包含所有留言信息的List
     */
    List<MessageEntity> getAllMessages();

    /**
     * 查询最近100条留言信息
     * @return 包含最近100条留言信息的List
     */
    List<MessageEntity> getLatest100Messages();

    /**
     * 查询最近200条留言信息
     * @return 包含最近200条留言信息的List
     */
    List<MessageEntity> getLatest200Messages();

    /**
     * 添加一条留言
     * @param messageEntity 留言的实例对象
     * @return 添加是否成功
     */
    boolean addMessage(MessageEntity messageEntity);
}
