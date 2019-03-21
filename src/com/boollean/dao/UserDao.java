package com.boollean.dao;

import com.boollean.entity.UserEntity;

import java.util.List;

public interface UserDao {
    public void init();
    public List<UserEntity> getAllUser();
    public List<UserEntity> listBest100Users4();
    public List<UserEntity> listBest100Users5();
    public List<UserEntity> listBest100Users6();
    public boolean isUserNameAvailable(String name);
    public boolean addUser(UserEntity userEntity);
    public UserEntity getUserByName(String oldName);
    public boolean updateUserByName(String name,UserEntity userEntity);
    public boolean deleteUserByName(String name);
    public void close();
}
