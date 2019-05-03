package com.boollean.service;

import com.boollean.Utils.RankUser;
import com.boollean.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务的接口
 *
 * @author Boollean
 */
@Service("userService")
public interface UserService {
    /**
     * 获取所有用户的信息
     *
     * @return 包含所有用户的List
     */
    List<UserEntity> getAllUsers();

    /**
     * 通过用户名来获取用户信息
     *
     * @return 单个用户对象
     */
    UserEntity getUserByName();

    /**
     * 获取用户的4*4模式最高分
     *
     * @return 4*4模式最高分
     */
    int getBestScore4ByName();

    /**
     * 获取用户的5*5模式最高分
     *
     * @return 5*5模式最高分
     */
    int getBestScore5ByName();

    /**
     * 获取用户的6*6模式最高分
     *
     * @return 6*6模式最高分
     */
    int getBestScore6ByName();

    /**
     * 获取4*4模式最高分的100个用户
     *
     * @return 包含用户的List
     */
    List<RankUser> listBest100Users4();

    /**
     * 获取5*5模式最高分的100个用户
     *
     * @return 包含用户的List
     */
    List<RankUser> listBest100Users5();

    /**
     * 获取6*6模式最高分的100个用户
     *
     * @return 包含用户的List
     */
    List<RankUser> listBest100Users6();

    /**
     * 查询数据库中是否存在用户名，不存在则可用
     *
     * @return 用户名也用与否
     */
    boolean isUserNameAvailable(String name);

    /**
     * 添加用户
     *
     * @return 添加是否成功
     */
    boolean addUser();

    /**
     * 通过用户名更新用户信息
     *
     * @return 更新是否成功
     */
    boolean updateUserByName();

    /**
     * 更新用户信息（除了用户名）
     *
     * @return 更改成功与否
     */
    boolean updateUserDataByName();

    /**
     * 更新用户4*4模式的最高记录
     *
     * @return 结果成功与否
     */
    boolean updateBestScore4ByName();

    /**
     * 更新用户5*5模式的最高记录
     *
     * @return 结果成功与否
     */
    boolean updateBestScore5ByName();

    /**
     * 更新用户6*6模式的最高记录
     *
     * @return 结果成功与否
     */
    boolean updateBestScore6ByName();

    /**
     * 根据用户名和密码删除用户
     *
     * @return 删除是否成功
     */
    boolean deleteUser();

    /**
     * 根据用户名更新用户头像
     *
     * @return 更新成功与否
     */
    boolean uploadImage();

    /**
     * 根据用户名获取头像
     *
     * @return 获取成功与否
     */
    boolean getImage();
}
