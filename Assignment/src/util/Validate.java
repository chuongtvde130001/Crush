package util;

public class Validate {
    //check thông tin người dùng nhập register có thíc hợp với CSDL
    public static boolean checkName(String name) {
        // Tên người dùng tối thiểu 3 kí tự và tối da 20
        return name.matches("[\\p{L}\\s]{3,20}");
    }

    public static boolean checkEmail(String email) {
        // chấp nhận email vd : email@address.com
        return email.matches("\\w+@\\w+[.]\\w"); 
    }
    
    public static boolean checkPassWord(String password){
        // Chấp nhận số .. tối thiểu 8 kí tự và tối đa 32
        return password.matches("\\w{8,32}");
    }

}
