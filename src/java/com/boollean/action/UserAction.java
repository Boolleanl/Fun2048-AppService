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

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport {

    @Resource
    private UserService userService;

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

    public void reSetJsonData() {
        jsonData.clear();
        jsonData.put("code", 200);
        jsonData.put("msg", "success");
    }

    public String getAllUsers() {
        List<UserEntity> list = userService.getAllUsers();
        reSetJsonData();
        jsonData.put("subjects", list);
        return "success";
    }

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

    public String getBestScore4ByName() {
        int score = userService.getBestScore4ByName();
        reSetJsonData();
        jsonData.put("bestScore4", score);
        return "success";
    }

    public String getBestScore5ByName() {
        int score = userService.getBestScore5ByName();
        reSetJsonData();
        jsonData.put("bestScore5", score);
        return "success";
    }

    public String getBestScore6ByName() {
        int score = userService.getBestScore6ByName();
        reSetJsonData();
        jsonData.put("bestScore6", score);
        return "success";
    }

    public String listBest100Users4() {
        List<RankUser> list = userService.listBest100Users4();
        reSetJsonData();
        jsonData.put("subjects", list);
        return "success";
    }

    public String listBest100Users5() {
        List<RankUser> list = userService.listBest100Users5();
        reSetJsonData();
        jsonData.put("subjects", list);
        return "success";
    }

    public String listBest100Users6() {
        List<RankUser> list = userService.listBest100Users6();
        reSetJsonData();
        jsonData.put("subjects", list);
        return "success";
    }

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
}
