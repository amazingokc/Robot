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
    private String trainnum;
    private String start;
    private String name;
    private String starttime;
    private String endtime;
    private String detailurl;
//    private String trainnum;




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

    public  String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public void setStart(String start) {
        this.start = start;
    }
    public String getStart() {
        return start;
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