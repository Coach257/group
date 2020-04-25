package com.test.myservlet;

public class TomcatTest {
    public static void main(String args[]){
                String str="com.test.myservlet.myservlet";
        try {
            Class clazz=Class.forName(str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
