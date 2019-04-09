package com.boollean.dao.impl;

import com.boollean.dao.MessageDao;
import com.boollean.entity.MessageEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Repository("messageDao")
public class MessageDaoImpl implements MessageDao {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    private Session session;

    /**
     * 查询所有留言
     *
     * @return 保存所有留言的列表
     */
    @Override
    public List<MessageEntity> getAllMessages() {
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM MessageEntity M ORDER BY M.id DESC";
        List<MessageEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        }
        return list;
    }

    @Override
    public List<MessageEntity> getLatest100Messages() {
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM MessageEntity M ORDER BY M.id DESC";
        List<MessageEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(100);
            list = query.list();
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                MessageEntity messageEntity = (MessageEntity) iterator.next();
                System.out.println("ID: " + messageEntity.getMsgid() + "  Name= " + messageEntity.getName());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    @Override
    public List<MessageEntity> getLatest1000Messages() {
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM MessageEntity M ORDER BY M.id DESC";
        List<MessageEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(1000);
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    @Override
    public boolean addMessage(MessageEntity messageEntity) {
        //取得session对象
        session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(messageEntity);
            transaction.commit();
            System.out.println("添加成功！ " + messageEntity.getName());
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
        }
    }
}
