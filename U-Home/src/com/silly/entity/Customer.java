package com.silly.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class Customer {
    private int Cnum;
    private String Name;
    private String Email;
    private String Phone;
    private String Code;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @JSONField(name = "Cnum")
    public int getCnum() {
        return Cnum;
    }

    @JSONField(name = "Name")
    public String getName() {
        return Name;
    }

    @JSONField(name = "Email")
    public String getEmail() {
        return Email;
    }

    @JSONField(name = "Phone")
    public String getPhone() {
        return Phone;
    }

    @JSONField(name = "Code")
    public String getCode() {
        return Code;
    }

    public void setCnum(int cnum) {
        Cnum = cnum;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setCode(String code) {
        Code = code;
    }

    public Customer(){

    }

    public Customer(int cnum, String name, String email, String phone, String code) {
        Cnum = cnum;
        Name = name;
        Email = email;
        Phone = phone;
        Code = code;
    }


    public String toString(){
        return "{\"cnum\":\""+Cnum+ "\"," +
                "\"name\":\""+ Name +"\"," +
                "\"email\":\""+ Email + "\"," +
                "\"code\":\""+ Code + "\"," +
                "\"phone\":\""+ Phone + "\""+
                "}";
    }
}
