package com.silly.service;

public interface SignLoginService {
    public Object login(String username,String password,String type);
    public void signin(int Cnum, String Name, String Email, String Phone, String Code);
    public boolean getbyname(String name);
}
