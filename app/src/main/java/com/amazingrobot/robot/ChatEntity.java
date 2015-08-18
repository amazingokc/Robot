package com.amazingrobot.robot;

import java.io.Serializable;
import java.util.Date;

public class ChatEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Type type;
    private Date date;
    private String msg;

    public enum Type {
        INCOMING, OUTCOMING
    }


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ChatEntity() {
        super();
        // TODO Auto-generated constructor stub
    }




    public ChatEntity(Type type, Date date, String msg) {
        super();
        this.type = type;
        this.date = date;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ChatEntity [type=" + type + ", date=" + date + ", msg=" + msg
                + "]";
    }

}