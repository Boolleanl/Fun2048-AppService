package com.boollean.action;

import com.boollean.entity.UserEntity;
import com.boollean.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
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

    public List<UserEntity> getAllUser() {
        System.out.println("查询所有用户。");
        request = ServletActionContext.getRequest();
        System.out.println("name=" + request.getParameter("username"));
        response = ServletActionContext.getResponse();

        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out;

        try {
            List<UserEntity> list = userService.getAllUsers();

            JSONArray jsonArray = new JSONArray();

            out = response.getWriter();


            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
