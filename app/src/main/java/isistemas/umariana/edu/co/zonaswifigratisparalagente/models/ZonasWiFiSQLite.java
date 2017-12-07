package isistemas.umariana.edu.co.zonaswifigratisparalagente.models;

import android.content.ContentValues;

import java.io.Serializable;

import isistemas.umariana.edu.co.zonaswifigratisparalagente.utilidades.Utilidades;

/**
 * Created by felipe on 28/11/17.
 */

public class ZonasWiFiSQLite {

    private String id_zona_wifi;
    private String nombre_zona_wifi;
    private String zona_inaguraruda;
    private String proyecto;
    private String direccion;
    private String latitud;
    private String longitud;
    private int no;
    private String region;
    private String departamento;
    private String municipio;
    private String item;

    public ZonasWiFiSQLite(String id_zona_wifi, String nombre_zona_wifi, String zona_inaguraruda, String proyecto, String direccion, String latitud, String longitud, int no, String region, String departamento, String municipio) {
        this.id_zona_wifi = id_zona_wifi;
        this.nombre_zona_wifi = nombre_zona_wifi;
        this.zona_inaguraruda = zona_inaguraruda;
        this.proyecto = proyecto;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.no = no;
        this.region = region;
        this.departamento = departamento;
        this.municipio = municipio;
    }

    public ZonasWiFiSQLite(String item) {
        this.item = item;
    }

    public String getId_zona_wifi() {
        return id_zona_wifi;
    }

    public void setId_zona_wifi(String id_zona_wifi) {
        this.id_zona_wifi = id_zona_wifi;
    }

    public String getNombre_zona_wifi() {
        return nombre_zona_wifi;
    }

    public void setNombre_zona_wifi(String nombre_zona_wifi) {
        this.nombre_zona_wifi = nombre_zona_wifi;
    }

    public String getZona_inaguraruda() {
        return zona_inaguraruda;
    }

    public void setZona_inaguraruda(String zona_inaguraruda) {
        this.zona_inaguraruda = zona_inaguraruda;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
