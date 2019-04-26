package com.boollean.service.impl;

import com.boollean.Utils.GetRequestBodyUtils;
import com.boollean.Utils.RankUser;
import com.boollean.dao.UserDao;
import com.boollean.entity.UserEntity;
import com.boollean.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Boollean
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public List<UserEntity> getAllUsers() {
        return this.userDao.getAllUsers();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public UserEntity getUserByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        if (name.trim().isEmpty()) {
            return null;
        }
        return this.userDao.getUserByName(name);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public int getBestScore4ByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        if (name.trim().isEmpty()) {
            return 0;
        }
        return this.userDao.getBestScore4ByName(name);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public int getBestScore5ByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        if (name.trim().isEmpty()) {
            System.out.println("姓名不能为空");
            return 0;
        }
        return this.userDao.getBestScore5ByName(name);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public int getBestScore6ByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        if (name.trim().isEmpty()) {
            System.out.println("姓名不能为空");
            return 0;
        }
        return this.userDao.getBestScore6ByName(name);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public List<RankUser> listBest100Users4() {
        List<UserEntity> list = this.userDao.listBest100Users4();
        List<RankUser> rankUsers = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            RankUser user = new RankUser();
            user.setPosition(i + 1);
            user.setName(list.get(i).getName());
            user.setGender(list.get(i).getGender());
            user.setAvatar(list.get(i).getAvatar());
            user.setScore(list.get(i).getBestscore4());
            rankUsers.add(user);
        }
        return rankUsers;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public List<RankUser> listBest100Users5() {
        List<UserEntity> list = this.userDao.listBest100Users5();
        List<RankUser> rankUsers = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            RankUser user = new RankUser();
            user.setPosition(i + 1);
            user.setName(list.get(i).getName());
            user.setGender(list.get(i).getGender());
            user.setAvatar(list.get(i).getAvatar());
            user.setScore(list.get(i).getBestscore5());
            rankUsers.add(user);
        }
        return rankUsers;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public List<RankUser> listBest100Users6() {
        List<UserEntity> list = this.userDao.listBest100Users6();
        List<RankUser> rankUsers = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            RankUser user = new RankUser();
            user.setPosition(i + 1);
            user.setName(list.get(i).getName());
            user.setGender(list.get(i).getGender());
            user.setAvatar(list.get(i).getAvatar());
            user.setScore(list.get(i).getBestscore6());
            rankUsers.add(user);
        }
        return rankUsers;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
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
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public boolean addUser() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String jsonString = null;
        try {
            jsonString = GetRequestBodyUtils.getRequestJsonString(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获得解析者
        JsonParser jsonParser = new JsonParser();
        //获得根节点元素
        JsonElement root = jsonParser.parse(jsonString);
        //根据文档判断根节点属于什么类型的Gson节点对象
        JsonObject element = root.getAsJsonObject();
        //取得节点下的某个节点的value

        JsonObject jsonObject = element.getAsJsonObject("subject");
        Gson gson = new Gson();
        UserEntity userEntity = gson.fromJson(jsonObject, UserEntity.class);
        if (isUserNameAvailable(userEntity.getName())) {
            return this.userDao.addUser(userEntity);
        } else return false;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public boolean updateUserByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String jsonString = null;
        try {
            jsonString = GetRequestBodyUtils.getRequestJsonString(request);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println(jsonString);
        //获得解析者
        JsonParser jsonParser = new JsonParser();
        //获得根节点元素
        JsonElement root = jsonParser.parse(jsonString);
        //根据文档判断根节点属于什么类型的Gson节点对象
        JsonObject object = root.getAsJsonObject();
        String oldName = object.get("oldName").getAsString();
        System.out.println(oldName);

        JsonObject jsonObject = object.getAsJsonObject("subject");
        System.out.println(jsonObject.toString());

        String newName = jsonObject.get("name").getAsString();
        int gender = jsonObject.get("gender").getAsInt();
        String password = jsonObject.get("password").getAsString();
        //String avatar = jsonObject.get("bitmapPath").getAsString();
        String avatar = "avatarPath";

        if (isUserNameAvailable(newName)) {
            return this.userDao.updateUserByName(oldName, newName, gender, password, avatar);
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public boolean updateUserDataByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String jsonString = null;
        try {
            jsonString = GetRequestBodyUtils.getRequestJsonString(request);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println(jsonString);
        //获得解析者
        JsonParser jsonParser = new JsonParser();
        //获得根节点元素
        JsonElement root = jsonParser.parse(jsonString);
        //根据文档判断根节点属于什么类型的Gson节点对象
        JsonObject object = root.getAsJsonObject();

        JsonObject jsonObject = object.getAsJsonObject("subject");
        System.out.println(jsonObject.toString());

        String name = jsonObject.get("name").getAsString();
        int gender = jsonObject.get("gender").getAsInt();
        String password = jsonObject.get("password").getAsString();
        //String avatar = jsonObject.get("bitmapPath").getAsString();
        String avatar = "avatarPath";

        return this.userDao.updateUserDataByName(name, gender, password, avatar);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public boolean updateBestScore4ByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        String s = request.getParameter("score");
        int score = Integer.parseInt(s);
        if (name.trim().isEmpty() || score < 0) {
            return false;
        }
        if (this.userDao.isUserNameAvailable(name)) {
            return false;
        }
        return this.userDao.updateBestScore4ByName(name, score);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public boolean updateBestScore5ByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        String s = request.getParameter("score");
        int score = Integer.parseInt(s);
        if (name.trim().isEmpty() || score < 0) {
            return false;
        }
        if (this.userDao.isUserNameAvailable(name)) {
            return false;
        }
        return this.userDao.updateBestScore5ByName(name, score);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public boolean updateBestScore6ByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        String s = request.getParameter("score");
        int score = Integer.parseInt(s);
        if (name.trim().isEmpty() || score < 0) {
            return false;
        }
        if (this.userDao.isUserNameAvailable(name)) {
            return false;
        }
        return this.userDao.updateBestScore6ByName(name, score);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public boolean deleteUser() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if (name.trim().isEmpty()) {
            return false;
        }
        if (this.userDao.isUserNameAvailable(name)) {
            return false;
        }
        return this.userDao.deleteUser(name,password);
    }
}
