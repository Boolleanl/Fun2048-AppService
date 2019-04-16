package com.boollean.service;

import com.boollean.Utils.RankUser;
import com.boollean.entity.UserEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("userService")
public interface UserService {
    List<UserEntity> getAllUsers();

    UserEntity getUserByName();

    int getBestScore4ByName();

    int getBestScore5ByName();

    int getBestScore6ByName();

    List<RankUser> listBest100Users4();

    List<RankUser> listBest100Users5();

    List<RankUser> listBest100Users6();

    boolean isUserNameAvailable(String name);

    boolean addUser();

    boolean updateUserByName(String name, UserEntity userEntity);

    boolean updateUserDataByName();

    boolean updateBestScore4ByName();

    boolean updateBestScore5ByName();

    boolean updateBestScore6ByName();

    boolean deleteUserByName();
}
