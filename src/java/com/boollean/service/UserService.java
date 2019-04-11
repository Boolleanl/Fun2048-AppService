package com.boollean.service;

import com.boollean.Utils.RankUser;
import com.boollean.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public interface UserService {
    List<UserEntity> getAllUsers();

    UserEntity getUserByName(String name);

    int getBestScore4ByName(String name);

    int getBestScore5ByName(String name);

    int getBestScore6ByName(String name);

    List<RankUser> listBest100Users4();

    List<RankUser> listBest100Users5();

    List<RankUser> listBest100Users6();

    boolean isUserNameAvailable(String name);

    boolean addUser(UserEntity userEntity);

    boolean updateUserByName(String name, UserEntity userEntity);

    boolean updateUserDataByName(String oldName, String newName, int gender, String password, String avatar);

    boolean updateBestScore4ByName(String name, int score);

    boolean updateBestScore5ByName(String name, int score);

    boolean updateBestScore6ByName(String name, int score);

    boolean deleteUserByName(String name);
}
