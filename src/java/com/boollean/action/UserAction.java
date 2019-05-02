package com.boollean.action;

import com.boollean.Utils.RankUser;
import com.boollean.entity.UserEntity;
import com.boollean.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Boollean
 */
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport {

    @Resource
    private UserService userService;

    //用于生成JSON数据的HashMap
    private Map<String, Object> jsonData = new HashMap<String, Object>();

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

    /**
     * 重置JsonData里的数据
     */
    public void reSetJsonData() {
        jsonData.clear();
        jsonData.put("code", 200);
        jsonData.put("msg", "success");
    }

    /**
     * 获取所有用户信息
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String getAllUsers() {
        List<UserEntity> list = userService.getAllUsers();
        reSetJsonData();
        jsonData.put("subjects", list);
        return "success";
    }

    /**
     * 根据用户名获取用户信息
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String getUserByName() {
        UserEntity userEntity = userService.getUserByName();
        reSetJsonData();
        if (userEntity != null) {
            jsonData.put("subject", userEntity);
            return "success";
        }
        jsonData.put("subject", null);
        return "success";
    }

    /**
     * 获取指定用户名用户的4*4模式最高分
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String getBestScore4ByName() {
        int score = userService.getBestScore4ByName();
        reSetJsonData();
        jsonData.put("bestScore4", score);
        return "success";
    }

    /**
     * 获取指定用户名用户的5*5模式最高分
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String getBestScore5ByName() {
        int score = userService.getBestScore5ByName();
        reSetJsonData();
        jsonData.put("bestScore5", score);
        return "success";
    }

    /**
     * 获取指定用户名用户的6*6模式最高分
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String getBestScore6ByName() {
        int score = userService.getBestScore6ByName();
        reSetJsonData();
        jsonData.put("bestScore6", score);
        return "success";
    }

    /**
     * 获取4*4模式最高分的前100个用户
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String listBest100Users4() {
        List<RankUser> list = userService.listBest100Users4();
        reSetJsonData();
        jsonData.put("subjects", list);
        return "success";
    }

    /**
     * 获取5*5模式最高分的前100个用户
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String listBest100Users5() {
        List<RankUser> list = userService.listBest100Users5();
        reSetJsonData();
        jsonData.put("subjects", list);
        return "success";
    }

    /**
     * 获取6*6模式最高分的前100用户
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String listBest100Users6() {
        List<RankUser> list = userService.listBest100Users6();
        reSetJsonData();
        jsonData.put("subjects", list);
        return "success";
    }

    /**
     * 判断用户名是否可用
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String isUserNameAvailable() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        reSetJsonData();
        if (userService.isUserNameAvailable(name)) {
            jsonData.put("subject", true);
        } else {
            jsonData.put("subject", false);
        }
        return "success";
    }

    /**
     * 新增一个用户
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String addUser() {
        jsonData.clear();
        jsonData.put("code", 200);
        if (userService.addUser()) {
            jsonData.put("msg", "success");
        } else {
            jsonData.put("msg", "fail");
        }
        return "success";
    }

    /**
     * 凭用户名更新用户的信息，包括用户名
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String updateUserByName() {
        jsonData.clear();
        jsonData.put("code", 200);
        if (userService.updateUserByName()) {
            jsonData.put("msg", "success");
        } else {
            jsonData.put("msg", "fail");
        }
        return "success";
    }

    /**
     * 凭用户名更新用户的信息，不包括用户名
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String updateUserDataByName() {
        jsonData.clear();
        jsonData.put("code", 200);
        if (userService.updateUserDataByName()) {
            jsonData.put("msg", "success");
        } else {
            jsonData.put("msg", "fail");
        }
        return "success";
    }

    /**
     * 凭用户名更新用户的4*4模式最高分
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String updateBestScore4ByName() {
        jsonData.clear();
        jsonData.put("code", 200);
        if (userService.updateBestScore4ByName()) {
            jsonData.put("msg", "success");
        } else {
            jsonData.put("msg", "fail");
        }
        return "success";
    }

    /**
     * 凭用户名更新用户的5*5模式最高分
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String updateBestScore5ByName() {
        jsonData.clear();
        jsonData.put("code", 200);
        if (userService.updateBestScore5ByName()) {
            jsonData.put("msg", "success");
        } else {
            jsonData.put("msg", "fail");
        }
        return "success";
    }

    /**
     * 凭用户名更新用户的6*6模式最高分
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String updateBestScore6ByName() {
        jsonData.clear();
        jsonData.put("code", 200);
        if (userService.updateBestScore6ByName()) {
            jsonData.put("msg", "success");
        } else {
            jsonData.put("msg", "fail");
        }
        return "success";
    }

    /**
     * 删除一个用户
     * @return 固定的字符串“success”，sturts会将JsonData里的数据自动转换成Json数据
     */
    public String deleteUser() {
        jsonData.clear();
        jsonData.put("code", 200);
        if (userService.deleteUser()) {
            jsonData.put("msg", "success");
        } else {
            jsonData.put("msg", "fail");
        }
        return "success";
    }

    public String uploadImage(){
        jsonData.clear();
        jsonData.put("code", 200);
        if (userService.uploadImage()) {
            jsonData.put("msg", "success");
        } else {
            jsonData.put("msg", "fail");
        }
        return "success";
    }

    public String getImage(){
        if(userService.getImage()){
            reSetJsonData();
        }else {
            jsonData.clear();
            jsonData.put("code", 200);
            jsonData.put("msg", "fail");
        }
        return "success";
    }
}
