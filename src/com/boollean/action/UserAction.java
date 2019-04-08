package com.boollean.action;

import antlr.Utils;
import com.boollean.entity.UserEntity;
import com.boollean.service.UserService;
import com.google.gson.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.Session;
import org.hibernate.annotations.Parent;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport {

    HttpServletRequest request;

    @Autowired
    @Qualifier("userService")
    UserService userService;

    private Map<String,Object> jsonData = new HashMap<String, Object>();

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    public String testAction(){
        System.out.println("login...action...");

        //获得request和response对象
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        //设置response输出json便于调试
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out;

        try{
            List<UserEntity> list = userService.getAllUsers();

            out = response.getWriter();
            JSONObject json = new JSONObject();

            //登录失败返回0   成功返回1
            if(list.isEmpty()) {
                json.put("msg", "0");
            } else {
                json.put("msg", list.get(1).getName());
            }
            System.out.println(json);
            out.write(json.toString());
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAllUser(){
        System.out.println("查询所有用户。");
        List<UserEntity> list = userService.getAllUsers();
        jsonData.clear();
        jsonData.put("success",true);
        jsonData.put("users",list);

        System.out.println(jsonData.toString());
        return "success";
    }

    @Test
    public void runTestAction(){
        getAllUser();
    }

    public String getUserByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        UserEntity userEntity = userService.getUserByName(name);
        jsonData.clear();
        if(userEntity!=null){
            jsonData.put("success",true);
            jsonData.put("user",userEntity);
            return "success";
        }
        jsonData.put("success",false);
        return "fail";
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

    public String getBestScore4ByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        int score = userService.getBestScore4ByName(name);
        jsonData.clear();
        jsonData.put("success",true);
        jsonData.put("bestScore4",score);
        return "success";
    }

    @Test
    public void testBestScore() {
        getBestScore4ByName();
    }

    public String getBestScore5ByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        int score = userService.getBestScore5ByName(name);
        jsonData.clear();
        jsonData.put("success",true);
        jsonData.put("bestScore5",score);
        return "success";
    }

    public String getBestScore6ByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        int score = userService.getBestScore6ByName(name);
        jsonData.clear();
        jsonData.put("success",true);
        jsonData.put("bestScore6",score);
        return "success";
    }

    public String listBest100Users4() {
        System.out.println("查询4*4最高分的100个用户。");
        List<UserEntity> list = userService.listBest100Users4();
        jsonData.clear();
        jsonData.put("success",true);
        jsonData.put("users",list);
        return "success";
    }

    public String listBest100Users5() {
        System.out.println("查询5*5最高分的100个用户。");
        List<UserEntity> list = userService.listBest100Users5();
        jsonData.clear();
        jsonData.put("success",true);
        jsonData.put("users",list);
        return "success";
    }

    public String listBest100Users6() {
        System.out.println("查询6*6高分用户。");
        List<UserEntity> list = userService.listBest100Users6();
        jsonData.clear();
        jsonData.put("success",true);
        jsonData.put("users",list);
        return "success";
    }

    public String isUserNameAvailable() {
        jsonData.clear();
        request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        if (userService.isUserNameAvailable(name)) {
            jsonData.put("success",true);
            return "true";
        }
        jsonData.put("success",false);
        return "false";
    }

    public String addUser() {
        jsonData.clear();
        request = ServletActionContext.getRequest();
        try {
            String jsonString = request.getQueryString();
            Gson gson = new Gson();
            UserEntity userEntity = gson.fromJson(jsonString, UserEntity.class);
            if (userService.addUser(userEntity)) {
                jsonData.put("success",true);
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonData.put("success",false);
        return "fail";
    }

    public String updateUserByName() {
        jsonData.clear();
        request = ServletActionContext.getRequest();
        String jsonString = request.getQueryString();

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
            jsonData.put("success",true);
            return "success";
        }
        jsonData.put("success",false);
        return "fail";
    }

    public String updateUserDataByName() {
        jsonData.clear();
        request = ServletActionContext.getRequest();
        String jsonString = request.getQueryString();

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
            jsonData.put("success",true);
            return "success";
        }
        jsonData.put("success",false);
        return "失败";
    }

    public String updateBestScore4ByName() {
        jsonData.clear();
        request = ServletActionContext.getRequest();
        String jsonString = request.getQueryString();

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
            jsonData.put("success",true);
            return "成功";
        }
        jsonData.put("success",false);
        return "失败";
    }

    public String updateBestScore5ByName() {
        jsonData.clear();
        request = ServletActionContext.getRequest();
        String jsonString = request.getQueryString();

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
            jsonData.put("success",true);
            return "success";
        }
        jsonData.put("success", false);
        return "fail";
    }

    public String updateBestScore6ByName() {
        jsonData.clear();
        request = ServletActionContext.getRequest();
        String jsonString = request.getQueryString();

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
            jsonData.put("success",true);
            return "success";
        }
        jsonData.put("success", false);
        return "fail";
    }

    public String deleteUserByName() {

        jsonData.clear();
        request = ServletActionContext.getRequest();
        String jsonString = request.getQueryString();

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
            jsonData.put("success",true);
            return "success";
        }
        jsonData.put("success", false);
        return "fail";
    }
}
