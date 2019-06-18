package model;

import java.util.Date;

public class Message {
    private int mid, fid, fr, status;
    private String content;
    private Date time;//

    public Message(int fid, int fr, String content) {
        this.fid = fid;
        this.fr = fr;
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

    public int getFr() {
        return fr;
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
