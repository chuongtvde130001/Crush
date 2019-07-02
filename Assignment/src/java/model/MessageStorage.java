package model;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageStorage {
    //HashMap <uid : <fid : messages>>
    public HashMap<Integer, HashMap<Integer, ArrayList<String>>> mesStorage;

    public MessageStorage(){
        mesStorage = new HashMap<>();
    }

    public synchronized void addMessage(int fid, int uid, String content){
        if(!mesStorage.containsKey(uid)) mesStorage.put(uid,new HashMap<>());
        if(!mesStorage.get(uid).containsKey(fid)) mesStorage.get(uid).put(fid, new ArrayList<>());
        mesStorage.get(uid).get(fid).add(content);
    }

    public synchronized HashMap<Integer,ArrayList<String>> getMessage(int uid) {
        HashMap<Integer,ArrayList<String>> hash;
        if(mesStorage.containsKey(uid)) {
            hash = (HashMap<Integer,ArrayList<String>>) mesStorage.get(uid).clone();
            mesStorage.get(uid).clear();
            return (hash.isEmpty())? null:hash;
        }
        return null;
    }

    public synchronized void clearMessage(int uid){
        try {
            mesStorage.get(uid).clear();
        }catch(Exception e){}
    }
}
