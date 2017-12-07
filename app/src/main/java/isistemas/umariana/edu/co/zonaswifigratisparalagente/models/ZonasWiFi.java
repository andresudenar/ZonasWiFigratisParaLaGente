package isistemas.umariana.edu.co.zonaswifigratisparalagente.models;

/**
 * Created by felipe on 28/11/17.
 */

import android.content.ContentValues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import isistemas.umariana.edu.co.zonaswifigratisparalagente.utilidades.Utilidades;

public class ZonasWiFi {
    @SerializedName("c_digo_dane_del_departamento")
    @Expose
    private String cDigoDaneDelDepartamento;
    @SerializedName("c_digo_dane_del_municipio")
    @Expose
    private String cDigoDaneDelMunicipio;
    @SerializedName("cantidad")
    @Expose
    private String cantidad;
    @SerializedName("categoria_de_departamento")
    @Expose
    private String categoriaDeDepartamento;
    @SerializedName("categoria_de_municipio")
    @Expose
    private String categoriaDeMunicipio;
    @SerializedName("departamento")
    @Expose
    private String departamento;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("id_zona_wifi")
    @Expose
    private String idZonaWifi;
    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("localidad")
    @Expose
    private String localidad;
    @SerializedName("longitud")
    @Expose
    private String longitud;
    @SerializedName("municipio")
    @Expose
    private String municipio;
    @SerializedName("no")
    @Expose
    private String no;
    @SerializedName("nombre_zona_wifi")
    @Expose
    private String nombreZonaWifi;
    @SerializedName("proyecto")
    @Expose
    private String proyecto;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("zona_inagurada")
    @Expose
    private String zonaInagurada;

    public String getCDigoDaneDelDepartamento() {
        return cDigoDaneDelDepartamento;
    }

    public void setCDigoDaneDelDepartamento(String cDigoDaneDelDepartamento) {
        this.cDigoDaneDelDepartamento = cDigoDaneDelDepartamento;
    }

    public String getCDigoDaneDelMunicipio() {
        return cDigoDaneDelMunicipio;
    }

    public void setCDigoDaneDelMunicipio(String cDigoDaneDelMunicipio) {
        this.cDigoDaneDelMunicipio = cDigoDaneDelMunicipio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoriaDeDepartamento() {
        return categoriaDeDepartamento;
    }

    public void setCategoriaDeDepartamento(String categoriaDeDepartamento) {
        this.categoriaDeDepartamento = categoriaDeDepartamento;
    }

    public String getCategoriaDeMunicipio() {
        return categoriaDeMunicipio;
    }

    public void setCategoriaDeMunicipio(String categoriaDeMunicipio) {
        this.categoriaDeMunicipio = categoriaDeMunicipio;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIdZonaWifi() {
        return idZonaWifi;
    }

    public void setIdZonaWifi(String idZonaWifi) {
        this.idZonaWifi = idZonaWifi;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNombreZonaWifi() {
        return nombreZonaWifi;
    }

    public void setNombreZonaWifi(String nombreZonaWifi) {
        this.nombreZonaWifi = nombreZonaWifi;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getZonaInagurada() {
        return zonaInagurada;
    }

    public void setZonaInagurada(String zonaInagurada) {
        this.zonaInagurada = zonaInagurada;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();

        values.put(Utilidades.ZonasWiFiEntry.ID, idZonaWifi);
        values.put(Utilidades.ZonasWiFiEntry.NOMBRE, nombreZonaWifi);
        values.put(Utilidades.ZonasWiFiEntry.ESTADO, zonaInagurada);
        values.put(Utilidades.ZonasWiFiEntry.PROYECTO, proyecto);
        values.put(Utilidades.ZonasWiFiEntry.DIR, direccion);
        values.put(Utilidades.ZonasWiFiEntry.DIR, direccion);
        values.put(Utilidades.ZonasWiFiEntry.LAT, latitud);
        values.put(Utilidades.ZonasWiFiEntry.LON, longitud);
        values.put(Utilidades.ZonasWiFiEntry.NUMERO, no);
        values.put(Utilidades.ZonasWiFiEntry.REGION, region);
        values.put(Utilidades.ZonasWiFiEntry.DEPARTAMENTO, departamento);
        values.put(Utilidades.ZonasWiFiEntry.MUNICIPIO, municipio);

        return values;
    }
}
