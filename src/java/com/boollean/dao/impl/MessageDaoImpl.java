package com.boollean.dao.impl;

import com.boollean.dao.MessageDao;
import com.boollean.entity.MessageEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Repository("messageDao")
@Transactional
public class MessageDaoImpl implements MessageDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    private Session session;
    //获取日志记录器Logger，名字为本类类名
    private static Logger logger = LogManager.getLogger(MessageDaoImpl.class);

    /**
     * 查询所有留言
     *
     * @return 保存所有留言的列表
     */
    @Override
    public List<MessageEntity> getAllMessages() {
        logger.info("读取所有留言信息");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM MessageEntity M ORDER BY M.id DESC";
        List<MessageEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            list = query.list();
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                MessageEntity messageEntity = (MessageEntity) iterator.next();
                System.out.println("Name: " + messageEntity.getDate());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        }
        return list;
    }

    @Override
    public List<MessageEntity> getLatest100Messages() {
        logger.info("读取100条留言信息");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM MessageEntity M ORDER BY M.id DESC";
        List<MessageEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(100);
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {}
        return list;
    }

    @Override
    public List<MessageEntity> getLatest200Messages() {
        logger.info("读取200条留言信息");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM MessageEntity M ORDER BY M.id DESC";
        List<MessageEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(200);
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {}
        return list;
    }

    @Override
    public boolean addMessage(MessageEntity messageEntity) {
        logger.info("新增一条留言信息");
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(messageEntity);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}
