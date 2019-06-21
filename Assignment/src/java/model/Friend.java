package model;

public class Friend {
    private int fid, userA, userB;

    public Friend(int fid, int userA, int userB) {
        this.fid = fid;
        this.userA = userA;
        this.userB = userB;
    }

    public int getFid() {
        return fid;
    }

    public int getUserA() {
        return userA;
    }

    public int getUserB() {
        return userB;
    }

    public int getFriendOf(int uid) {
        return (userA==uid) ? userB:userA;
    }
}
