package com.boollean.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "messagebox", schema = "appservice", catalog = "")
public class MessageboxEntity {
    private String userName;
    private Timestamp time;
    private String msg;
    private int msgid;

    @Basic
    @Column(name = "name", nullable = false, length = 12)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Id
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
        MessageboxEntity that = (MessageboxEntity) o;
        return msgid == that.msgid &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(time, that.time) &&
                Objects.equals(msg, that.msg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, time, msg, msgid);
    }
}
