package model;

public class Message {
    private int fid, from;
    private String content;

    public Message(){}

    public Message(int fid, int from, String content) {
        this.fid = fid;
        this.from = from;
        this.content = content;
    }

    public int getFid() {
        return fid;
    }

    public int getFrom() {
        return from;
    }

    public String getContent() {
        return content;
    }
}
