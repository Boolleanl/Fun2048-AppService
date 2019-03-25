package com.boollean.dao.impl;

import com.boollean.dao.UserDao;
import com.boollean.entity.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Repository(value = "USER")
@Transactional
public class UserDaoImpl implements UserDao {

    private static Configuration configuration;
    private static SessionFactory factory;
    private static Session session;

    @Override
    public void init() {
        //1.加载配置文件,设置配置文件,默认加载src目录下的hibernate.cfg.xml的配置文件
        configuration = new Configuration().configure();
        //2.建立SessionFactory对象
        factory = configuration.buildSessionFactory();
        //3.打开session对象
        session = factory.openSession();
    }

    /**
     * 查询所有用户
     *
     * @return 集合所有用户的列表
     */
    @Override
    public List<UserEntity> getAllUsers() {
        init();
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
            close();
        }
        return list;
    }

    @Test
    public void testGetAllUser() {
        getAllUsers();
    }

    @Override
    public int getBestScore4ByName(String name) {
        int result = 0;
        init();
        String hql = "SELECT U.bestscore4 FROM UserEntity U WHERE U.name = :name";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            if (query.list().get(0) == null) {
                System.out.println("4*4模式最高分： " + result);
                return result;
            }
            result = (int) query.list().get(0);
            System.out.println("4*4模式最高分： " + result);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    @Test
    public void testGetBestScore() {
        getBestScore4ByName("小Y");
        getBestScore4ByName("小方");
        getBestScore5ByName("小Y");
        getBestScore5ByName("小方");
        getBestScore6ByName("小Y");
        getBestScore6ByName("小方");
    }

    @Override
    public int getBestScore5ByName(String name) {
        int result = 0;
        init();
        String hql = "SELECT U.bestscore5 FROM UserEntity U WHERE U.name = :name";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            if (query.list().get(0) == null) {
                System.out.println("5*5模式最高分： " + result);
                return result;
            }
            result = (int) query.list().get(0);
            System.out.println("5*5模式最高分： " + result);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    @Override
    public int getBestScore6ByName(String name) {
        int result = 0;
        init();
        String hql = "SELECT U.bestscore6 FROM UserEntity U WHERE U.name = :name";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            if (query.list().get(0) == null) {
                System.out.println("6*6模式最高分： " + result);
                return result;
            }
            result = (int) query.list().get(0);
            System.out.println("6*6模式最高分： " + result);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }

    /**
     * 查询4*4模式分数最高的前一百个用户
     *
     * @return
     */
    @Override
    public List<UserEntity> listBest100Users4() {
        init();
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
            close();
        }
        return list;
    }

    @Test
    public void testListBest100Users4() {
        listBest100Users4();
    }

    /**
     * 查询5*5模式分数最高的前一百个用户
     *
     * @return
     */
    @Override
    public List<UserEntity> listBest100Users5() {
        init();
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
            close();
        }
        return list;
    }

    @Test
    public void testListBest100Users5() {
        listBest100Users5();
    }

    /**
     * 查询6*6模式分数最高的前一百个用户
     *
     * @return
     */
    @Override
    public List<UserEntity> listBest100Users6() {
        init();
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
            close();
        }
        return list;
    }

    @Test
    public void testListBest100Users6() {
        listBest100Users6();
    }

    /**
     * 检查用户名是否可用，未曾在数据库出现且不为空则可用
     *
     * @return
     */
    @Override
    public boolean isUserNameAvailable(String name) {
        init();
        String queryString = "FROM UserEntity U WHERE U.name = ?1";
        try {
            Query query = session.createQuery(queryString).setParameter(1, name);
            if (query.list().isEmpty()) {
                return true;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return false;
    }

    @Test
    public void testName() {
        isUserNameAvailable("小日");
        isUserNameAvailable("  ");
        isUserNameAvailable("小佳");
        isUserNameAvailable("");
    }

    /**
     * 加入一个用户到数据库中
     *
     * @return
     */
    @Override
    public boolean addUser(UserEntity userEntity) {
        init();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(userEntity);
            transaction.commit();
            System.out.println("添加成功！ " + userEntity.getName());
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
    public void testAddUser() {
        UserEntity u1 = new UserEntity("小河", "q7a41", 2, "d45sd1");
        UserEntity u2 = new UserEntity("小日", "q7a41", 2, "d45sd1");
        addUser(u1);
        addUser(u2);
    }

    /**
     * 通过姓名来查询用户
     *
     * @return
     */
    @Override
    public UserEntity getUserByName(String name) {
        init();
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
            close();
        }
        UserEntity userEntity = list.get(0);
        System.out.println(userEntity.getName());
        return userEntity;
    }

    @Test
    public void teatGetByName() {
        getUserByName("");
        getUserByName("小日");
        getUserByName("  ");
        getUserByName("小小");

    }

    /**
     * 通过姓名来更新用户信息
     *
     * @param oldName    更改信息前的用户姓名
     * @param userEntity 新的用户信息生成的用户实体
     * @return
     */
    @Transactional
    @Override
    public boolean updateUserByName(String oldName, UserEntity userEntity) {
        init();
        Transaction transaction = null;
        String hql2 = "UPDATE UserEntity U set U = :user WHERE U.name = :oldName";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql2);
            query.setParameter("user", userEntity);
            query.setParameter("oldName", oldName);
            int result = query.executeUpdate();
            transaction.commit();
            System.out.println("添加成功！ " + result);
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
    public void testUpdate() {
        UserEntity u = new UserEntity("小D", "q7a41", 2, "d45sd1");
        updateUserByName("大的", u);

    }

    /**
     * 更新用户4*4模式的最高记录
     *
     * @param name  用户名
     * @param score 分数
     * @return 结果成功与否
     */
    @Transactional
    @Override
    public boolean updateBestScore4(String name, int score) {
        init();
        Transaction transaction = null;
        String hql = "UPDATE UserEntity U set U.bestscore4 = :score "
                + "WHERE U.name = :name";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("score", score);
            query.setParameter("name", name);
            transaction.commit();
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
    public void testUpdateBestScore4() {
        updateBestScore4("小Y", 4444);
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
    public boolean updateBestScore5(String name, int score) {
        if (!name.trim().isEmpty()) {
            init();
            Transaction transaction = null;
            String hql = "UPDATE UserEntity U set U.bestscore5 = :score "
                    + "WHERE U.name = :name";
            try {
                transaction = session.beginTransaction();
                Query query = session.createQuery(hql);
                query.setParameter("score", score);
                query.setParameter("name", name);
                int result = query.executeUpdate();
                transaction.commit();
                System.out.println("更新成功！ " + result);
                return true;
            } catch (HibernateException e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
                factory.close();
            }
        } else {
            System.out.println(" 失败");
        }
        return false;
    }

    @Test
    public void testUpdateBestScore5() {
        updateBestScore5("小Y", 55555);
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
    public boolean updateBestScore6(String name, int score) {
        if (!name.trim().isEmpty()) {
            init();
            Transaction transaction = null;
            String hql = "UPDATE UserEntity U set U.bestscore6 = :score "
                    + "WHERE U.name = :name";
            try {
                transaction = session.beginTransaction();
                Query query = session.createQuery(hql);
                query.setParameter("score", score);
                query.setParameter("name", name);
                int result = query.executeUpdate();
                transaction.commit();
                System.out.println("更新成功！ " + result);
                return true;
            } catch (HibernateException e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
                factory.close();
            }
        } else {
            System.out.println(" 失败");
        }
        return false;
    }

    @Test
    public void testUpdateBestScore6() {
        updateBestScore6("小Y", 666666);
    }

    @Transactional
    @Override
    public boolean updateUserByName(String oldName, String newName, int gender, String password, String avatar) {
        init();
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
            int result = query.executeUpdate();
            transaction.commit();
            System.out.println("更新成功！ " + result);
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
    public void testUpdateByName() {
        updateUserByName("小红", "晓红", 0, "741", "357");
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
        String hql = "DELETE FROM UserEntity U WHERE U.name = ?1";
        Transaction transaction = null;
        try {
            init();
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
            close();
        }
    }

    @Test
    public void testDelete() {
        deleteUserByName("小华");
    }

    @Override
    public void close() {
        session.close();
        factory.close();
    }
}
