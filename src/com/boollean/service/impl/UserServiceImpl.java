package com.boollean.service.impl;

import com.boollean.dao.UserDao;
import com.boollean.entity.UserEntity;
import com.boollean.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return this.userDao.getAllUsers();
    }

    @Override
    public UserEntity getUserByName(String name) {
        if (name.trim().isEmpty()) {
            return null;
        }
        return this.userDao.getUserByName(name);
    }

    @Override
    public int getBestScore4ByName(String name) {
        if (name.trim().isEmpty()) {
            System.out.println("姓名不能为空");
            return 0;
        }
        return this.userDao.getBestScore4ByName(name);
    }

    @Override
    public int getBestScore5ByName(String name) {
        if (name.trim().isEmpty()) {
            System.out.println("姓名不能为空");
            return 0;
        }
        return this.userDao.getBestScore5ByName(name);
    }

    @Override
    public int getBestScore6ByName(String name) {
        if (name.trim().isEmpty()) {
            System.out.println("姓名不能为空");
            return 0;
        }
        return this.userDao.getBestScore6ByName(name);
    }

    @Override
    public List<UserEntity> listBest100Users4() {
        return this.userDao.listBest100Users4();
    }

    @Override
    public List<UserEntity> listBest100Users5() {
        return this.userDao.listBest100Users5();
    }

    @Override
    public List<UserEntity> listBest100Users6() {
        return this.userDao.listBest100Users6();
    }

    @Override
    public boolean isUserNameAvailable(String name) {
        if (name.trim().isEmpty()) {
            return false;
        }
        if (this.userDao.isUserNameAvailable(name)) {
            return true;
        } else {
            System.out.println("改名字已存在");
            return false;
        }
    }

    @Override
    public boolean addUser(UserEntity userEntity) {
        if (isUserNameAvailable(userEntity.getName())) {
            return this.userDao.addUser(userEntity);
        } else return false;
    }

    @Override
    public boolean updateUserByName(String name, UserEntity userEntity) {
        if (isUserNameAvailable(userEntity.getName())) {
            return this.userDao.updateUserByName(name, userEntity);
        } else return false;
    }

    @Override
    public boolean updateUserByName(String oldName, String newName, int gender, String password, String avatar) {
        if (isUserNameAvailable(newName)) {
            return this.userDao.updateUserByName(oldName, newName, gender, password, avatar);
        } else return false;
    }

    @Override
    public boolean updateBestScore4(String name, int score) {
        if (name.trim().isEmpty() || score < 0) {
            return false;
        }
        return this.userDao.updateBestScore4(name, score);
    }

    @Override
    public boolean updateBestScore5(String name, int score) {
        if (name.trim().isEmpty() || score < 0) {
            return false;
        }
        return this.userDao.updateBestScore5(name, score);
    }

    @Override
    public boolean updateBestScore6(String name, int score) {
        if (name.trim().isEmpty() || score < 0) {
            return false;
        }
        return this.userDao.updateBestScore6(name, score);
    }

    @Override
    public boolean deleteUserByName(String name) {
        if (name.trim().isEmpty()) {
            return false;
        }
        return this.userDao.deleteUserByName(name);
    }
}
