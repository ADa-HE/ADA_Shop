/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author ADA
 */
public class Account {
    String user, pass;
    int roll;

    @Override
    public String toString() {
        return "Account{" + "user=" + user + ", pass=" + pass + ", roll=" + roll + '}';
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public Account(String user, String pass, int roll) {
        this.user = user;
        this.pass = pass;
        this.roll = roll;
    }

    public Account() {
    }
}
