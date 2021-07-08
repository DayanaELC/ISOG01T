package com.example.comprale;

import android.content.Intent;
import android.widget.ImageView;

import java.util.ArrayList;

public class Variables {
    static String LoginId, LoginDir, LoginImagen, LoginUser;
    static String LoginEmail = "";
    static String LoginPass = "";
    static int ItemGridProductos;
    static int CodigoProducto;
    static double totalPagar;
    static boolean iniciado = false;

    static String Producto, Precio, Cantidad, Imagen;

    static ArrayList<Integer>ListaCodigoProducto=new ArrayList<>();
    static ArrayList<String>ListaNombreProducto=new ArrayList<>();
    static ArrayList<String>ListaPrecioProducto=new ArrayList<>();
    static ArrayList<String>ListaCantidadProducto=new ArrayList<>();
    static ArrayList<String>ListaImagen=new ArrayList<>();

    static ArrayList<Integer>ImagenesIdHome=new ArrayList<>();
    static ArrayList<String>ImagenesHome=new ArrayList<>();

    static ArrayList<String> ListaImagenNuevo=new ArrayList<>();
    static ArrayList<String> ListaNombresNuevo=new ArrayList<>();
    static ArrayList<Double> ListaPreciosNuevo=new ArrayList<>();
    static ArrayList<String> ListaInfoNuevo=new ArrayList<>();

    public ArrayList<String> getListaImagenNuevo() {
        return ListaImagenNuevo;
    }

    public ArrayList<String> getListaNombresNuevo() {
        return ListaNombresNuevo;
    }

    public ArrayList<Double> getListaPreciosNuevo() {
        return ListaPreciosNuevo;
    }

    public ArrayList<String> getListaInfoNuevo() {
        return ListaInfoNuevo;
    }

    public ArrayList<Integer> getImagenesIdHome() {
        return ImagenesIdHome;
    }

    public ArrayList<String> getImagenesHome() {
        return ImagenesHome;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        Variables.totalPagar = totalPagar;
    }

    public boolean isIniciado() {
        return iniciado;
    }

    public void setIniciado(boolean iniciado) {
        Variables.iniciado = iniciado;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
        ArregloImagen();
    }

    public void setCodigoProducto(int codigoProducto) {
        CodigoProducto = codigoProducto;
        ArregloCodigoProducto();
    }

    public void setProducto(String producto) {
        Producto = producto;
        ArregloNombreProducto();
    }

    public void setPrecio(String precio) {
        Precio = precio;
        ArregloPrecioProducto();
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
        ArregloCantidadProducto();
    }

    public String getLoginImagen() {
        return LoginImagen;
    }

    public void setLoginImagen(String loginImagen) {
        LoginImagen = loginImagen;
    }

    public String getLoginDir() {
        return LoginDir;
    }

    public void setLoginDir(String loginDir) {
        LoginDir = loginDir;
    }

    public String getLoginId() {
        return LoginId;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }

    public String getLoginEmail() {
        return LoginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        LoginEmail = loginEmail;
    }

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

    public void ArregloCodigoProducto(){
        ListaCodigoProducto.add(CodigoProducto);
    }

    public void ArregloImagen(){
        ListaImagen.add(Imagen);
    }

    public void ArregloNombreProducto(){
        ListaNombreProducto.add(Producto);
    }

    public void ArregloPrecioProducto(){
        ListaPrecioProducto.add(Precio);
    }

    public void ArregloCantidadProducto(){
        ListaCantidadProducto.add(Cantidad);
    }

    public ArrayList<String> getListaNombreProducto() {
        return ListaNombreProducto;
    }

    public ArrayList<String> getListaPrecioProducto() {
        return ListaPrecioProducto;
    }

    public ArrayList<String> getListaCantidadProducto() {
        return ListaCantidadProducto;
    }

    public ArrayList<Integer> getListaCodigoProducto() { return ListaCodigoProducto; }

    public ArrayList<String> getListaImagen() {
        return ListaImagen;
    }
}
