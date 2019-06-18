/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Md5Encryptor;

public class User {
    private String password,userName,fullName,gender,email;
    private int uid,age;

    public User() {
    }

    public User(String userName, String fullName, String gender, String email, int uid, int age) {
        this.userName = userName;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.uid = uid;
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Md5Encryptor.getMd5(password);
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
}
