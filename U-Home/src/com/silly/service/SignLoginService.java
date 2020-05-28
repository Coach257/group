package com.silly.service;

public interface SignLoginService {
    public Object login(String username,String password,String type);
    public String signup(int Cnum, String Name, String Email, String Phone, String Code);
}
