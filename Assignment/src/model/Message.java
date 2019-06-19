package model;

import java.util.Date;

public class Message {
    private int mid, fid, from, status;
    private String content;
    private Date time;

    public Message(){
        this.time = new Date();
        this.status = 0;
    }

    public Message(int fid, int from, String content) {
        this.fid = fid;
        this.from = from;
        this.content = content;
        this.time = new Date();
        this.status = 0;
    }

    public int getMid() {
        return mid;
    }

    public int getFid() {
        return fid;
    }

    public int getFrom() {
        return from;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    public Date getTime() {
        return time;
    }
}
