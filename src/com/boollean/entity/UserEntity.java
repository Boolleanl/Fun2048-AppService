package com.boollean.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "appservice", catalog = "")
public class UserEntity {
    private String name;
    private String password;
    private int gender;
    private String avatar;
    private Integer bestscore4;
    private Integer bestscore5;
    private Integer bestscore6;

    public UserEntity(String name, String password, int gender, String avatar) {
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.avatar = avatar;
    }

    public UserEntity(String name, String password, int gender, String avatar, Integer bestscore4, Integer bestscore5, Integer bestscore6) {
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.avatar = avatar;
        this.bestscore4 = bestscore4;
        this.bestscore5 = bestscore5;
        this.bestscore6 = bestscore6;
    }

    public UserEntity() {}

    @Id
    @Column(name = "name", nullable = false, length = 12)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 18)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "gender", nullable = false)
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "avatar", nullable = true, length = 100)
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Column(name = "bestscore4", nullable = true)
    public Integer getBestscore4() {
        return bestscore4;
    }

    public void setBestscore4(Integer bestscore4) {
        this.bestscore4 = bestscore4;
    }

    @Basic
    @Column(name = "bestscore5", nullable = true)
    public Integer getBestscore5() {
        return bestscore5;
    }

    public void setBestscore5(Integer bestscore5) {
        this.bestscore5 = bestscore5;
    }

    @Basic
    @Column(name = "bestscore6", nullable = true)
    public Integer getBestscore6() {
        return bestscore6;
    }

    public void setBestscore6(Integer bestscore6) {
        this.bestscore6 = bestscore6;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return gender == that.gender &&
                Objects.equals(name, that.name) &&
                Objects.equals(password, that.password) &&
                Objects.equals(avatar, that.avatar) &&
                Objects.equals(bestscore4, that.bestscore4) &&
                Objects.equals(bestscore5, that.bestscore5) &&
                Objects.equals(bestscore6, that.bestscore6);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, gender, avatar, bestscore4, bestscore5, bestscore6);
    }
}
