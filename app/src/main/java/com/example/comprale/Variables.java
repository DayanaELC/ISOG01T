package com.example.comprale;

import android.content.Intent;

public class Variables {
    static String LoginUser, LoginPass;
    static int ItemGridProductos;

    public int getItemGridProductos() {
        return ItemGridProductos;
    }

    public void setItemGridProductos(int itemGridProductos) {
        ItemGridProductos = itemGridProductos;
    }

    public String getLoginUser() {
        return LoginUser;
    }

    public void setLoginUser(String loginUser) {
        LoginUser = loginUser;
    }

    public String getLoginPass() {
        return LoginPass;
    }

    public void setLoginPass(String loginPass) {
        LoginPass = loginPass;
    }
}
