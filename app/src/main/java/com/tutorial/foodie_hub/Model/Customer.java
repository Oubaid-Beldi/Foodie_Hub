package com.tutorial.foodie_hub.Model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String pwd;
    private String mail;
    private String id;
    private String suggestion;
    private  int numOfCoupon;

    public Customer(String name, String pwd, String mail, String suggestion,int numOfCoupon, String id ) {
        this.name = name;
        this.pwd = pwd;
        this.mail = mail;
        this.id = id;
        this.suggestion = suggestion;
        this.numOfCoupon = numOfCoupon;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Customer(String name, String pwd, String mail) {
        this.name = name;
        this.pwd = pwd;
        this.mail = mail;
    }




    public Customer(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public Customer() {
    }

    public Customer(String name, String pwd, String id, String suggestion, int numOfCoupon, ArrayList<Dish> orderd) {
        this.name = name;
        this.pwd = pwd;
        this.id = id;
        this.suggestion = suggestion;
        this.numOfCoupon = numOfCoupon;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public int getNumOfCoupon() {
        return numOfCoupon;
    }

    public void setNumOfCoupon(int numOfCoupon) {
        this.numOfCoupon = numOfCoupon;
    }




}
