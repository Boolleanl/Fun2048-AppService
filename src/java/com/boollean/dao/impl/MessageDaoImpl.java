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
import java.util.List;

/**
 * @author Boollean
 */
@Repository("messageDao")
@Transactional
public class MessageDaoImpl implements MessageDao {
    //获取日志记录器Logger，名字为本类类名
    private static Logger logger = LogManager.getLogger(MessageDaoImpl.class);
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    private Session session;

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
            logger.info("获取了 "+list.size()+" 条留言信息");
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
            //获取最多100条记录
            query.setMaxResults(100);
            list = query.list();
            logger.info("获取了 "+list.size()+" 条留言信息");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        }
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
            //获取最多200条记录
            query.setMaxResults(200);
            list = query.list();
            logger.info("获取了 "+list.size()+" 条留言信息");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        }
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
            logger.info("新增信息成功！");
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.info("新增信息失败！");
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}
