package com.boollean.Utils;

/**
 * 排行榜用户类
 *
 * @author Boollean
 */
public class RankUser {

    private int position;   //排名
    private String name;    //用户名
    private int gender;     //性别
    private Integer score;  //分数
    private String avatar;  //头像位置

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
