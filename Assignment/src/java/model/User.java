package model;

import utils.MD5;

public class User {

    private String password, userName, fullName, gender, email, avatar;
    private int uid, age, status;

    public User() {
    }

    public User(String password, String userName, String fullName, String gender, String email, String avatar, int uid, int age, int status) {
        this.password = password;
        this.userName = userName;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.avatar = avatar;
        this.uid = uid;
        this.age = age;
        this.status = status;
    }

    public User(String userName, String password, String email) {
        this.password = password;
        this.userName = userName;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = MD5.getMd5(password);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uID) {
        this.uid = uID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
