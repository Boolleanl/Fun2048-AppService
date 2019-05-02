package com.boollean.dao;

import com.boollean.entity.UserEntity;

import java.util.List;

/**
 * @author Boollean
 */
public interface UserDao {

    /**
     * 查询所有用户信息
     * @return 包含所有用户的List
     */
    List<UserEntity> getAllUsers();

    /**
     * 通过用户名查询用户信息
     * @param oldName 用户名
     * @return 用户实例对象
     */
    UserEntity getUserByName(String oldName);

    /**
     * 通过用户名查询用户的4*4模式分数
     * @param name 用户名
     * @return 分数
     */
    int getBestScore4ByName(String name);

    /**
     * 通过用户名查询用户的5*5模式分数
     * @param name 用户名
     * @return 分数
     */
    int getBestScore5ByName(String name);

    /**
     * 通过用户名查询用户的6*6模式分数
     * @param name 用户名
     * @return 分数
     */
    int getBestScore6ByName(String name);

    /**
     * 查询4*4模式排名前100的用户
     * @return 包含用户的List
     */
    List<UserEntity> listBest100Users4();

    /**
     * 查询5*5模式排名前100的用户
     * @return 包含用户的List
     */
    List<UserEntity> listBest100Users5();

    /**
     * 查询6*6模式排名前100的用户
     * @return 包含用户的List
     */
    List<UserEntity> listBest100Users6();

    /**
     * 查询数据库中是否存在用户名，不存在则可用
     * @param name 用户名是否可用
     * @return
     */
    boolean isUserNameAvailable(String name);

    /**
     * 添加用户
     * @param userEntity 需要添加的用户对象
     * @return 添加是否成功
     */
    boolean addUser(UserEntity userEntity);

    /**
     * 通过用户名更新用户信息
     * @param oldName 旧的用户名
     * @param newName 新的用户名
     * @param gender 新的性别
     * @param password 新的密码
     * @return 更新是否成功
     */
    boolean updateUserByName(String oldName, String newName, int gender, String password);

    /**
     * 更新用户信息（除了用户名）
     * @param name     用户名
     * @param gender   新的性别
     * @param password 新的密码
     * @return 更改成功与否
     */
    boolean updateUserDataByName(String name, int gender, String password);

    /**
     * 更新用户4*4模式的最高记录
     * @param name  用户名
     * @param score 分数
     * @return 结果成功与否
     */
    boolean updateBestScore4ByName(String name, int score);

    /**
     * 更新用户5*5模式的最高记录
     * @param name  用户名
     * @param score 分数
     * @return 结果成功与否
     */
    boolean updateBestScore5ByName(String name, int score);

    /**
     * 更新用户6*6模式的最高记录
     * @param name  用户名
     * @param score 分数
     * @return 结果成功与否
     */
    boolean updateBestScore6ByName(String name, int score);

    /**
     * 根据用户名和密码删除用户
     * @param name 用户名
     * @param password 密码
     * @return 删除是否成功
     */
    boolean deleteUser(String name ,String password);

    /**
     * 根据用户名更新用户头像
     * @param name 用户名
     * @param avatarPath 头像所在的位置
     * @return 更新是否成功
     */
    boolean updateAvatar(String name, String avatarPath);

    /**
     * 根据用户名获取头像
     * @param name
     * @return
     */
    String getAvatarByName(String name);
}
