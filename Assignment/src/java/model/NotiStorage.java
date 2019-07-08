package model;

import java.util.ArrayList;
import java.util.HashMap;

public class NotiStorage {
    private HashMap<Integer, HashMap<String, ArrayList<Integer>>> notiStorage;

    public NotiStorage() {
        this.notiStorage = new HashMap<>();
    }

    // 1 is friend, 2 is crush
    public void addNoti(int uid, int target, int notiType) {
        if (notiStorage.get(target) == null)
            notiStorage.put(target, new HashMap<>());
        switch (notiType) {
            case 1:
                if (notiStorage.get(target).get("friend") == null)
                    notiStorage.get(target).put("friend", new ArrayList<>());
                notiStorage.get(target).get("friend").add(uid);
                break;
            case 2:
                if (notiStorage.get(target).get("crush") == null)
                    notiStorage.get(target).put("crush", new ArrayList<>());
                notiStorage.get(target).get("crush").add(uid);
        }
    }

    public HashMap<String, ArrayList<Integer>> getNotis(int uid) {
        if(notiStorage.get(uid)==null) return null;
        HashMap<String, ArrayList<Integer>> out = (HashMap<String, ArrayList<Integer>>) notiStorage.get(uid).clone();
        if (out.size() > 0)
            notiStorage.get(uid).clear();
        return out;
    }
}
