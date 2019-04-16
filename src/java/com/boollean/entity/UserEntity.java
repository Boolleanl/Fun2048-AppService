package com.boollean.entity;

import javax.persistence.*;

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

        if (gender != that.gender) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (avatar != null ? !avatar.equals(that.avatar) : that.avatar != null) return false;
        if (bestscore4 != null ? !bestscore4.equals(that.bestscore4) : that.bestscore4 != null) return false;
        if (bestscore5 != null ? !bestscore5.equals(that.bestscore5) : that.bestscore5 != null) return false;
        if (bestscore6 != null ? !bestscore6.equals(that.bestscore6) : that.bestscore6 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + gender;
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (bestscore4 != null ? bestscore4.hashCode() : 0);
        result = 31 * result + (bestscore5 != null ? bestscore5.hashCode() : 0);
        result = 31 * result + (bestscore6 != null ? bestscore6.hashCode() : 0);
        return result;
    }
}
