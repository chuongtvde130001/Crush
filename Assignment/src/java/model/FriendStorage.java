package model;

import dao.FriendDAO;

import java.util.HashMap;

public class FriendStorage {
    private HashMap<Integer, Friend> friStorage;

    public FriendStorage(){
        friStorage = new HashMap<>();
    }

    public synchronized int getMyFriend(int fid, int userA) {
        int userB;
        try {
            userB = friStorage.get(fid).getFriendOf(userA);
        }catch (Exception e){
            userB = FriendDAO.getFriUid(fid,userA);
            if(friStorage.get(fid)==null) friStorage.put(fid, new Friend(fid, userA, userB));
        }
        return userB;
    }
}
