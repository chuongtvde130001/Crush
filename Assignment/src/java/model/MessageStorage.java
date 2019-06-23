package model;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageStorage {
    //HashMap <fid : <uid : messages>>
    public HashMap<Integer, HashMap<Integer, ArrayList<String>>> mesStorage;

    public MessageStorage(){
        mesStorage = new HashMap<>();
    }

    public synchronized void addMessage(int fid, int uid, String content){
        if(!mesStorage.containsKey(fid)) mesStorage.put(fid,new HashMap<>());
        if(!mesStorage.get(fid).containsKey(uid)) mesStorage.get(fid).put(uid, new ArrayList<>());
        mesStorage.get(fid).get(uid).add(content);
    }

    public synchronized ArrayList<String> getMessage(int fid, int uid) {
        ArrayList<String> list = null;
        if(mesStorage.containsKey(fid)) {
            list = (ArrayList<String>)mesStorage.get(fid).get(uid).clone();
            mesStorage.get(fid).get(uid).clear();
        }
        return list;
    }

    public static void main(String[] args) {
    }
}
