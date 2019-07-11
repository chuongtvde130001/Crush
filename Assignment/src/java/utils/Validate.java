package utils;

import dao.UserDAO;

public class Validate {

    public static boolean checkEmail(String email) {
        return UserDAO.checkEmail(email);
    }
    public static boolean checkUserName(String username) {
        return UserDAO.checkUsersName(username);
    }
}
