package com.boollean.dao.impl;

import com.boollean.dao.UserDao;
import com.boollean.entity.UserEntity;
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

@Repository(value = "userDao")
@Transactional
public class UserDaoImpl implements UserDao {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    private Session session;

    //获取日志记录器Logger，名字为本类类名
    private static Logger logger = LogManager.getLogger(UserDaoImpl.class);

    /**
     * 查询所有用户
     *
     * @return 集合所有用户的列表
     */
    @Override
    public List<UserEntity> getAllUsers() {
        logger.info("读取所有用户信息");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            list = query.list();
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                UserEntity userEntity = (UserEntity) iterator.next();
                System.out.println("Name: " + userEntity.getName());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }


    /**
     * 读取指定姓名用户的4*4模式最高分
     * @param name 用户名
     * @return 4*4模式最高分
     */
    @Override
    public int getBestScore4ByName(String name) {
        logger.info("读取 "+name+" 的4*4模式最高分");
        int result = 0;
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "SELECT U.bestscore4 FROM UserEntity U WHERE U.name = :name";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            if (query.list().isEmpty()) {
                System.out.println("4*4模式最高分： " + result);
                return result;
            }
            result = (int) query.list().get(0);
            System.out.println("4*4模式最高分： " + result);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    /**
     * 读取指定姓名用户的5*5模式最高分
     * @param name 用户名
     * @return 5*5模式最高分
     */
    @Override
    public int getBestScore5ByName(String name) {
        logger.info("读取 "+name+" 的5*5模式最高分");
        int result = 0;
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "SELECT U.bestscore5 FROM UserEntity U WHERE U.name = :name";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            if (query.list().isEmpty()) {
                System.out.println("5*5模式最高分： " + result);
                return result;
            }
            result = (int) query.list().get(0);
            System.out.println("5*5模式最高分： " + result);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    /**
     * 读取指定姓名用户的6*6模式最高分
     * @param name 用户名
     * @return 6*6模式最高分
     */
    @Override
    public int getBestScore6ByName(String name) {
        logger.info("读取 "+name+" 的6*6模式最高分");
        int result = 0;
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "SELECT U.bestscore6 FROM UserEntity U WHERE U.name = :name";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            if (query.list().isEmpty()) {
                System.out.println("6*6模式最高分： " + result);
                return result;
            }
            result = (int) query.list().get(0);
            System.out.println("6*6模式最高分： " + result);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    /**
     * 查询4*4模式分数最高的前一百个用户
     *
     * @return 包含一百人的List
     */
    @Override
    public List<UserEntity> listBest100Users4() {
        logger.info("4*4模式最高分的前一百人");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity U WHERE U.bestscore4 > 0 ORDER BY U.bestscore4 DESC ,U.name asc";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(100);
            list = query.list();
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                UserEntity userEntity = (UserEntity) iterator.next();
                System.out.print("Name: " + userEntity.getName());
                System.out.println("  4*4分数： " + userEntity.getBestscore4());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    /**
     * 查询5*5模式分数最高的前一百个用户
     *
     * @return 包含一百人的List
     */
    @Override
    public List<UserEntity> listBest100Users5() {
        logger.info("5*5模式最高分的前一百人");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity U WHERE U.bestscore5 > 0 ORDER BY U.bestscore5 DESC ,U.name asc";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(100);
            list = query.list();
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                UserEntity userEntity = (UserEntity) iterator.next();
                System.out.print("Name: " + userEntity.getName());
                System.out.println("  5*5分数： " + userEntity.getBestscore5());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    /**
     * 查询6*6模式分数最高的前一百个用户
     *
     * @return 包含一百人的List
     */
    @Override
    public List<UserEntity> listBest100Users6() {
        logger.info("6*6模式最高分的前一百人");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity U WHERE U.bestscore6 > 0 ORDER BY U.bestscore6 DESC ,U.name asc";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(100);
            list = query.list();
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                UserEntity userEntity = (UserEntity) iterator.next();
                System.out.print("Name: " + userEntity.getName());
                System.out.println("  6*6分数： " + userEntity.getBestscore6());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    /**
     * 检查用户名是否可用，未曾在数据库出现且不为空则可用
     *
     * @return
     */
    @Override
    public boolean isUserNameAvailable(String name) {
        logger.info("判断 "+name+" 是否在数据库内");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String queryString = "FROM UserEntity U WHERE U.name = ?1";
        try {
            Query query = session.createQuery(queryString).setParameter(1, name);
            if (query.list().isEmpty()) {
                return true;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        }
        return false;
    }

    /**
     * 加入一个用户到数据库中
     *
     * @return 加入成功与否
     */
    @Override
    public boolean addUser(UserEntity userEntity) {
        logger.info("加入一个用户到数据库");
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(userEntity);
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

    /**
     * 通过姓名来查询用户
     *
     * @return 用户对象
     */
    @Override
    public UserEntity getUserByName(String name) {
        logger.info("获取 " + name + " 的信息");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity U WHERE U.name = ?1";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql).setParameter(1, name);
            list = query.list();
            if (list.isEmpty()) {
                return null;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        }
        UserEntity userEntity = list.get(0);
        System.out.println(userEntity.getName());
        return userEntity;
    }

    /**
     * 通过姓名来更新用户信息
     *
     * @param oldName    更改信息前的用户姓名
     * @param userEntity 新的用户信息生成的用户实体
     * @return 更新成功与否
     */
    @Transactional
    @Override
    public boolean updateUserByName(String oldName, UserEntity userEntity) {
        logger.info("将原来存在的 "+oldName+" 用户信息更新");
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql2 = "UPDATE UserEntity U set U = :user WHERE U.name = :oldName";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql2);
            query.setParameter("user", userEntity);
            query.setParameter("oldName", oldName);
            query.executeUpdate();
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

    /**
     * 根据参数更改用户信息
     * @param oldName 原来的用户名，作为判断依据
     * @param newName 新的用户名
     * @param gender 新的性别
     * @param password 新的密码
     * @param avatar 新的头像
     * @return 更改成功与否
     */
    @Transactional
    @Override
    public boolean updateUserDataByName(String oldName, String newName, int gender, String password, String avatar) {
        logger.info("将原有的 "+oldName+" 用户信息更新");
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql = "UPDATE UserEntity U set U.name = :newName, U.avatar = :avatar, U.gender = :gender, U.password = :password "
                + "WHERE U.name = :oldName";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("newName", newName);
            query.setParameter("avatar", avatar);
            query.setParameter("gender", gender);
            query.setParameter("password", password);
            query.setParameter("oldName", oldName);
            query.executeUpdate();
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

    /**
     * 更新用户4*4模式的最高记录
     *
     * @param name  用户名
     * @param score 分数
     * @return 结果成功与否
     */
    @Override
    public boolean updateBestScore4ByName(String name, int score) {
        logger.info("更新 "+name+" 用户的4*4模式最高分");
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql = "UPDATE UserEntity U set U.bestscore4 = :score "
                + "WHERE U.name = :name";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("score", score);
            query.setParameter("name", name);
            query.executeUpdate();
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

    /**
     * 更新用户5*5模式的最高记录
     *
     * @param name  用户名
     * @param score 分数
     * @return 结果成功与否
     */
    @Transactional
    @Override
    public boolean updateBestScore5ByName(String name, int score) {
        logger.info("更新 "+name+" 用户的5*5模式最高分");
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql = "UPDATE UserEntity U set U.bestscore5 = :score "
                + "WHERE U.name = :name";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("score", score);
            query.setParameter("name", name);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    /**
     * 更新用户6*6模式的最高记录
     *
     * @param name  用户名
     * @param score 分数
     * @return 结果成功与否
     */
    @Transactional
    @Override
    public boolean updateBestScore6ByName(String name, int score) {
        logger.info("更新 "+name+" 用户的6*6模式最高分");
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql = "UPDATE UserEntity U set U.bestscore6 = :score "
                + "WHERE U.name = :name";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("score", score);
            query.setParameter("name", name);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }


    /**
     * 通过姓名来删除用户
     *
     * @param name 用户姓名
     * @return
     */
    @Transactional
    @Override
    public boolean deleteUserByName(String name) {
        logger.info("删除 "+name+" 的信息");
        String hql = "DELETE FROM UserEntity U WHERE U.name = ?1";
        Transaction transaction = null;
        try {
            //取得session对象
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter(1, name);
            query.executeUpdate();
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
