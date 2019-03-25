package com.boollean.service;

import com.boollean.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();

    UserEntity getUserByName(String name);

    int getBestScore4ByName(String name);

    int getBestScore5ByName(String name);

    int getBestScore6ByName(String name);

    List<UserEntity> listBest100Users4();

    List<UserEntity> listBest100Users5();

    List<UserEntity> listBest100Users6();

    boolean isUserNameAvailable(String name);

    boolean addUser(UserEntity userEntity);

    boolean updateUserByName(String name, UserEntity userEntity);

    boolean updateUserByName(String oldName, String newName, int gender, String password, String avatar);

    boolean updateBestScore4(String name, int score);

    boolean updateBestScore5(String name, int score);

    boolean updateBestScore6(String name, int score);

    boolean deleteUserByName(String name);
}
