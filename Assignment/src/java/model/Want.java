/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author duong
 */
public class Want {
        private int uid,ageBegin,ageEnd,Gender;

    public Want(int uid, int ageBegin, int ageEnd, int Gender) {
        this.uid = uid;
        this.ageBegin = ageBegin;
        this.ageEnd = ageEnd;
        this.Gender = Gender;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getAgeBegin() {
        return ageBegin;
    }

    public void setAgeBegin(int ageBegin) {
        this.ageBegin = ageBegin;
    }

    public int getAgeEnd() {
        return ageEnd;
    }

    public void setAgeEnd(int ageEnd) {
        this.ageEnd = ageEnd;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int Gender) {
        this.Gender = Gender;
    }
        
        
}
