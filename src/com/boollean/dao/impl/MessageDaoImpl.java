package com.boollean.dao.impl;

import com.boollean.dao.MessageDao;
import com.boollean.entity.MessageEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

@Repository(value = "MESSAGE")
@Transactional
public class MessageDaoImpl implements MessageDao {

    private static Configuration configuration;
    @Autowired
    @Qualifier("sessionFactory")
    private static SessionFactory sessionFactory;
    private static Session session;

    @Override
    public void init() {
        //1.加载配置文件,设置配置文件,默认加载src目录下的hibernate.cfg.xml的配置文件
        configuration = new Configuration().configure();
        //2.建立SessionFactory对象
        sessionFactory = configuration.buildSessionFactory();
        //3.打开session对象
        session = sessionFactory.openSession();
    }

    /**
     * 查询所有留言
     *
     * @return 保存所有留言的列表
     */
    @Override
    public List<MessageEntity> getAllMessages() {
        init();
        String hql = "FROM MessageEntity M ORDER BY M.id DESC";
        List<MessageEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    @Test
    public void testGetAllMessage() {
        getAllMessages();
    }

    @Override
    public List<MessageEntity> getLatest100Messages() {
        init();
        String hql = "FROM MessageEntity M ORDER BY M.id DESC";
        List<MessageEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(100);
            list = query.list();
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                MessageEntity messageEntity = (MessageEntity) iterator.next();
                System.out.println("ID: " + messageEntity.getMsgid() + "  Name= " + messageEntity.getUserName());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    @Override
    public List<MessageEntity> getLatest1000Messages() {
        init();
        String hql = "FROM MessageEntity M ORDER BY M.id DESC";
        List<MessageEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(1000);
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    @Override
    public boolean addMessage(MessageEntity messageEntity) {
        init();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(messageEntity);
            transaction.commit();
            System.out.println("添加成功！ " + messageEntity.getUserName());
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            close();
        }
    }

    @Test
    public void testAddMessage() {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setUserName("ku");
        messageEntity.setMsg("OK!");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        messageEntity.setTime(timestamp);
        addMessage(messageEntity);
    }

    @Override
    public void close() {
        session.close();
        sessionFactory.close();
    }

    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
    }
}
