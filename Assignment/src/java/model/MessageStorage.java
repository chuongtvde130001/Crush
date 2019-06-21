package model;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageStorage {
    public HashMap<Integer, HashMap<Integer, ArrayList<String>>> mesStorage;

    public MessageStorage(){
        mesStorage = new HashMap<>();
    }

    public synchronized void addMessage(int uid, int fid, String content){
        if(!mesStorage.containsKey(uid)) mesStorage.put(uid,new HashMap<>());
        if(!mesStorage.get(uid).containsKey(fid)) mesStorage.get(uid).put(fid, new ArrayList<>());
        mesStorage.get(uid).get(fid).add(content);
    }

    public synchronized ArrayList<String> getMessage(int uid, int fid) {
        ArrayList<String> list = null;
        if(mesStorage.containsKey(uid)) {
            list = mesStorage.get(uid).get(fid);
            mesStorage.get(uid).get(fid).clear();
        }
        return list;
    }

    public static void main(String[] args) {
        MessageStorage mes = new MessageStorage();
//        mes.addMessage(1,1,"xx");
        System.out.println(mes.getMessage(1,1));
//        System.out.println(mes.getMessage(1,1)==null);
    }
}
