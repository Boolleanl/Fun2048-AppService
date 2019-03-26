package com.boollean.action;

import com.boollean.entity.UserEntity;
import com.boollean.service.UserService;
import com.google.gson.*;
import com.opensymphony.xwork2.ActionSupport;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserAction extends ActionSupport {

    HttpServletRequest request;
    HttpServletResponse response;
    UserService userService;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getAllUser() {
        System.out.println("查询所有用户。");
        List<UserEntity> list = userService.getAllUsers();
        Gson gson = new Gson();
        String result = gson.toJson(list);
        System.out.println(result);
        return result;
    }

    public String getUserByName(String name) {
        UserEntity user = userService.getUserByName(name);
        Gson gson = new Gson();
        String result = gson.toJson(user);
        System.out.println(result);
        return result;
    }

    @Test
    public void testGetUserByName() {
        UserEntity user1 = new UserEntity();
        user1.setName("小猫");
        user1.setPassword("maomao");
        user1.setAvatar("gfdgd");
        user1.setGender(2);
        user1.setBestscore4(1234);
        user1.setBestscore5(12345);
        user1.setBestscore6(123456);
        UserEntity user2 = new UserEntity();
        user2.setName("小哈");
        user2.setPassword("hahaha");
        user2.setAvatar("gfdgd");
        user2.setGender(1);
        user2.setBestscore4(102);
        user2.setBestscore5(12034);
        user2.setBestscore6(123006);
        List<UserEntity> list = new ArrayList();
        list.add(user1);
        list.add(user2);
        list.add(user1);
    }

    public String getBestScore4ByName(String name) {
        int score = userService.getBestScore4ByName(name);
        String result = String.valueOf(score);
        System.out.println(result);
        return result;
    }

    @Test
    public void testBestScore() {
        getBestScore4ByName("qew");
    }

    public String getBestScore5ByName(String name) {
        int score = userService.getBestScore5ByName(name);
        String result = String.valueOf(score);
        System.out.println(result);
        return result;
    }

    public String getBestScore6ByName(String name) {
        int score = userService.getBestScore6ByName(name);
        String result = String.valueOf(score);
        System.out.println(result);
        return result;
    }

    public String listBest100Users4() {
        System.out.println("查询4*4高分用户。");
        List<UserEntity> list = userService.listBest100Users4();
        Gson gson = new Gson();
        String result = gson.toJson(list);
        System.out.println(result);
        return result;
    }

    public String listBest100Users5() {
        System.out.println("查询5*5高分用户。");
        List<UserEntity> list = userService.listBest100Users5();
        Gson gson = new Gson();
        String result = gson.toJson(list);
        System.out.println(result);
        return result;
    }

    public String listBest100Users6() {
        System.out.println("查询6*6高分用户。");
        List<UserEntity> list = userService.listBest100Users6();
        Gson gson = new Gson();
        String result = gson.toJson(list);
        System.out.println(result);
        return result;
    }

    public String isUserNameAvailable(String name) {
        if (userService.isUserNameAvailable(name)) {
            return "可用";
        }
        return "不可用";
    }

    public String addUser(String jsonString) {
        Gson gson = new Gson();
        UserEntity userEntity = gson.fromJson(jsonString, UserEntity.class);
        if (userService.addUser(userEntity)) {
            return "成功";
        }
        return "失败";
    }

    public String updateUserByName(String jsonString) {
        //获得解析者
        JsonParser jsonParser = new JsonParser();
        //获得根节点元素
        JsonElement root = jsonParser.parse(jsonString);
        //根据文档判断根节点属于什么类型的Gson节点对象
        JsonObject element = root.getAsJsonObject();
        //取得节点下的某个节点的value
        JsonPrimitive oldNameJson = element.getAsJsonPrimitive("oldName");
        String oldName = oldNameJson.getAsString();

        JsonObject jsonObject = element.getAsJsonObject("user");

        Gson gson = new Gson();
        UserEntity userEntity = gson.fromJson(jsonObject, UserEntity.class);

        if (userService.updateUserByName(oldName, userEntity)) {
            return "成功";
        }
        return "失败";
    }

    public String updateUserDataByName(String jsonString) {
        //获得解析者
        JsonParser jsonParser = new JsonParser();
        //获得根节点元素
        JsonElement root = jsonParser.parse(jsonString);
        //根据文档判断根节点属于什么类型的Gson节点对象
        JsonObject element = root.getAsJsonObject();
        //取得节点下的某个节点的value
        JsonPrimitive oldNameJson = element.getAsJsonPrimitive("oldName");
        String oldName = oldNameJson.getAsString();
        JsonPrimitive newNameJson = element.getAsJsonPrimitive("newName");
        String newName = newNameJson.getAsString();
        JsonPrimitive genderJson = element.getAsJsonPrimitive("gender");
        String g = genderJson.getAsString();
        int gender = Integer.parseInt(g);
        JsonPrimitive passwordJson = element.getAsJsonPrimitive("password");
        String password = passwordJson.getAsString();
        JsonPrimitive avatarJson = element.getAsJsonPrimitive("avatar");
        String avatar = avatarJson.getAsString();

        if (userService.updateUserDataByName(oldName, newName, gender, password, avatar)) {
            return "成功";
        }
        return "失败";
    }

    public String updateBestScore4ByName(String jsonString) {
        //获得解析者
        JsonParser jsonParser = new JsonParser();
        //获得根节点元素
        JsonElement root = jsonParser.parse(jsonString);
        //根据文档判断根节点属于什么类型的Gson节点对象
        JsonObject element = root.getAsJsonObject();
        //取得节点下的某个节点的value
        JsonPrimitive nameJson = element.getAsJsonPrimitive("name");
        String name = nameJson.getAsString();
        JsonPrimitive bestScore4Json = element.getAsJsonPrimitive("bestScore4");
        String bestScore = bestScore4Json.getAsString();
        int score = Integer.parseInt(bestScore);

        if (userService.updateBestScore4ByName(name, score)) {
            return "成功";
        }
        return "失败";
    }

    public String updateBestScore5ByName(String jsonString) {
        //获得解析者
        JsonParser jsonParser = new JsonParser();
        //获得根节点元素
        JsonElement root = jsonParser.parse(jsonString);
        //根据文档判断根节点属于什么类型的Gson节点对象
        JsonObject element = root.getAsJsonObject();
        //取得节点下的某个节点的value
        JsonPrimitive nameJson = element.getAsJsonPrimitive("name");
        String name = nameJson.getAsString();
        JsonPrimitive bestScore5Json = element.getAsJsonPrimitive("bestScore5");
        String bestScore = bestScore5Json.getAsString();
        int score = Integer.parseInt(bestScore);

        if (userService.updateBestScore5ByName(name, score)) {
            return "成功";
        }
        return "失败";
    }

    public String updateBestScore6ByName(String jsonString) {
        //获得解析者
        JsonParser jsonParser = new JsonParser();
        //获得根节点元素
        JsonElement root = jsonParser.parse(jsonString);
        //根据文档判断根节点属于什么类型的Gson节点对象
        JsonObject element = root.getAsJsonObject();
        //取得节点下的某个节点的value
        JsonPrimitive nameJson = element.getAsJsonPrimitive("name");
        String name = nameJson.getAsString();
        JsonPrimitive bestScore6Json = element.getAsJsonPrimitive("bestScore6");
        String bestScore = bestScore6Json.getAsString();
        int score = Integer.parseInt(bestScore);

        if (userService.updateBestScore6ByName(name, score)) {
            return "成功";
        }
        return "失败";
    }

    public String deleteUserByName(String jsonString) {
        //获得解析者
        JsonParser jsonParser = new JsonParser();
        //获得根节点元素
        JsonElement root = jsonParser.parse(jsonString);
        //根据文档判断根节点属于什么类型的Gson节点对象
        JsonObject element = root.getAsJsonObject();
        //取得节点下的某个节点的value
        JsonPrimitive nameJson = element.getAsJsonPrimitive("name");
        String name = nameJson.getAsString();

        if (userService.deleteUserByName(name)) {
            return "成功";
        }
        return "失败";
    }
}
