package com.boollean.action;

import com.boollean.Utils.GetRequestBodyUtils;
import com.boollean.Utils.RankUser;
import com.boollean.entity.UserEntity;
import com.boollean.service.UserService;
import com.boollean.service.impl.UserServiceImpl;
import com.google.gson.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport {

    private UserEntity userEntity;

    public UserEntity getUserEntity(){
        return userEntity;
    }

    @Resource
    private UserService userService;

    private Map<String,Object> jsonData = new HashMap<String, Object>();

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

    public String getAllUser(){
        System.out.println("查询所有用户。");
        List<UserEntity> list = userService.getAllUsers();
        jsonData.clear();
        jsonData.put("code",200);
        jsonData.put("msg","success");
        jsonData.put("subjects",list);

        return "success";
    }

    public String getUserByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        UserEntity userEntity = userService.getUserByName(name);
        jsonData.clear();
        if(userEntity!=null){
            jsonData.put("code",200);
            jsonData.put("msg","success");
            jsonData.put("subject",userEntity);
            return "success";
        }
        jsonData.put("code",200);
        jsonData.put("msg","fail");
        jsonData.put("subject",null);
        return "success";
    }

    public String getBestScore4ByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        int score = userService.getBestScore4ByName(name);
        jsonData.clear();
        jsonData.put("code",200);
        jsonData.put("msg","success");
        jsonData.put("bestScore4",score);
        return "success";
    }

    public String getBestScore5ByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        int score = userService.getBestScore5ByName(name);
        jsonData.clear();
        jsonData.put("code",200);
        jsonData.put("msg","success");
        jsonData.put("bestScore5",score);
        return "success";
    }

    public String getBestScore6ByName() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        int score = userService.getBestScore6ByName(name);
        jsonData.clear();
        jsonData.put("code",200);
        jsonData.put("msg","success");
        jsonData.put("bestScore6",score);
        return "success";
    }

    public String listBest100Users4() {
        List<RankUser> list = userService.listBest100Users4();
        jsonData.clear();
        jsonData.put("code",200);
        jsonData.put("msg","success");
        jsonData.put("subjects",list);
        return "success";
    }

    public String listBest100Users5() {
        List<RankUser> list = userService.listBest100Users5();
        jsonData.clear();
        jsonData.put("code",200);
        jsonData.put("msg","success");
        jsonData.put("subjects",list);
        return "success";
    }

    public String listBest100Users6() {
        List<RankUser> list = userService.listBest100Users6();
        jsonData.clear();
        jsonData.put("code",200);
        jsonData.put("msg","success");
        jsonData.put("subjects",list);
        return "success";
    }

    public String isUserNameAvailable() {
        jsonData.clear();
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        jsonData.put("code",200);
        jsonData.put("msg","success");
        if (userService.isUserNameAvailable(name)) {
            jsonData.put("subject",true);
            return "success";
        }
        jsonData.put("subject",false);
        return "success";
    }

    public String addUser() {
        jsonData.clear();
        HttpServletRequest request = ServletActionContext.getRequest();
        try {
            String jsonString = GetRequestBodyUtils.getRequestJsonString(request);
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
            if (userService.addUser(userEntity)) {
                jsonData.put("subject","OK");
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonData.put("subject","FAIL");
        return "success";
    }

    public String updateUserByName() {
        jsonData.clear();
        HttpServletRequest request = ServletActionContext.getRequest();
        try {
            String jsonString = GetRequestBodyUtils.getRequestJsonString(request);
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
            Gson gson = new Gson();
            UserEntity userEntity = gson.fromJson(jsonObject, UserEntity.class);
            if (userService.updateUserByName(oldName,userEntity)) {
                jsonData.put("subject","OK");
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonData.put("subject","FAIL");
        return "success";
    }

//    public String updateUserDataByName() {
//        jsonData.clear();
//        request = ServletActionContext.getRequest();
//        String jsonString = request.getQueryString();
//
//        //获得解析者
//        JsonParser jsonParser = new JsonParser();
//        //获得根节点元素
//        JsonElement root = jsonParser.parse(jsonString);
//        //根据文档判断根节点属于什么类型的Gson节点对象
//        JsonObject element = root.getAsJsonObject();
//        //取得节点下的某个节点的value
//        JsonPrimitive oldNameJson = element.getAsJsonPrimitive("oldName");
//        String oldName = oldNameJson.getAsString();
//        JsonPrimitive newNameJson = element.getAsJsonPrimitive("newName");
//        String newName = newNameJson.getAsString();
//        JsonPrimitive genderJson = element.getAsJsonPrimitive("gender");
//        String g = genderJson.getAsString();
//        int gender = Integer.parseInt(g);
//        JsonPrimitive passwordJson = element.getAsJsonPrimitive("password");
//        String password = passwordJson.getAsString();
//        JsonPrimitive avatarJson = element.getAsJsonPrimitive("avatar");
//        String avatar = avatarJson.getAsString();
//
//        if (userService.updateUserDataByName(oldName, newName, gender, password, avatar)) {
//            jsonData.put("success",true);
//            return "success";
//        }
//        jsonData.put("success",false);
//        return "失败";
//    }

    public String updateBestScore4ByName() {
        jsonData.clear();
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        String s = request.getParameter("score");
        int score = Integer.parseInt(s);
        jsonData.put("code",200);
        jsonData.put("msg","success");
        if (userService.updateBestScore4ByName(name, score)) {
            jsonData.put("subject","OK");
            return "success";
        }
        jsonData.put("subject","FAIL");
        return "success";
    }

    public String updateBestScore5ByName() {
        jsonData.clear();
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        String s = request.getParameter("score");
        int score = Integer.parseInt(s);
        jsonData.put("code",200);
        jsonData.put("msg","success");
        if (userService.updateBestScore5ByName(name, score)) {
            jsonData.put("subject","OK");
            return "success";
        }
        jsonData.put("subject","FAIL");
        return "success";
    }

    public String updateBestScore6ByName() {
        jsonData.clear();
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        String s = request.getParameter("score");
        int score = Integer.parseInt(s);
        jsonData.put("code",200);
        jsonData.put("msg","success");
        if (userService.updateBestScore6ByName(name, score)) {
            jsonData.put("subject","OK");
            return "success";
        }
        jsonData.put("subject","FAIL");
        return "success";
    }

    public String deleteUserByName() {
        jsonData.clear();
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        jsonData.put("code",200);
        jsonData.put("msg","success");
        if (userService.deleteUserByName(name)) {
            jsonData.put("subject","OK");
            return "success";
        }
        jsonData.put("subject", "FAIL");
        return "success";
    }
}
