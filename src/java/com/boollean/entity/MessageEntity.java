package com.boollean.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "message", schema = "appservice", catalog = "")
public class MessageEntity {
    private String name;
    private Timestamp time;
    private String msg;
    private int msgid;
//    private UserEntity userByName;

    @Basic
    @Column(name = "name", nullable = false, length = 12)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "msg", nullable = false, length = 140)
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Basic
    @Column(name = "msgid", nullable = false)
    public int getMsgid() {
        return msgid;
    }

    public void setMsgid(int msgid) {
        this.msgid = msgid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity that = (MessageEntity) o;

        if (msgid != that.msgid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (msg != null ? !msg.equals(that.msg) : that.msg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        result = 31 * result + msgid;
        return result;
    }

//    @ManyToOne
//    @JoinColumn(name = "name", referencedColumnName = "name", nullable = false)
//    public UserEntity getUserByName() {
//        return userByName;
//    }
//
//    public void setUserByName(UserEntity userByName) {
//        this.userByName = userByName;
//    }
}
