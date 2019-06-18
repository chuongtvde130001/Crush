package java.ws;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import java.model.Message;

public class Test {
    public static void ppp(String s){
        System.out.println((new JsonParser().parse(s)).getAsJsonObject().get("id"));
    }
    public static void main(String args[]){
        Gson g = new Gson();//
        System.out.println((new JsonParser().parse("{'id':'clien t1'}")).getAsJsonObject().get("id"));
        Message mes = new Message(1,2,"aaaa");
        System.out.println(g.toJson(mes));
    }
}
