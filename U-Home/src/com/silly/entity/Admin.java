package com.silly.entity;

public class Admin {
    private int Anum;
    private String Name;
    private String Code;

    public int getAnum() {
        return Anum;
    }

    public String getName() {
        return Name;
    }

    public String getCode() {
        return Code;
    }

    public void setAnum(int anum) {
        Anum = anum;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCode(String code) {
        Code = code;
    }

    public Admin(int anum, String name, String code) {
        Anum = anum;
        Name = name;
        Code = code;
    }

    public Admin(){

    }
}
