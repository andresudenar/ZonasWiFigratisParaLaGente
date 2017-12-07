package isistemas.umariana.edu.co.zonaswifigratisparalagente.utilidades;

import android.provider.BaseColumns;

/**
 * Created by felipe on 28/11/17.
 */

public class Utilidades {

    public static abstract class ZonasWiFiEntry implements BaseColumns {

        public static final String TABLA = "zonas";

        public static final String ID = "id_zona_wifi";
        public static final String NOMBRE = "nombre_zona_wifi";
        public static final String ESTADO = "zona_inaguraruda";
        public static final String PROYECTO = "proyecto";
        public static final String DIR = "direccion";
        public static final String LAT = "latitud";
        public static final String LON = "longitud";
        public static final String NUMERO = "numero";
        public static final String REGION = "region";
        public static final String DEPARTAMENTO = "departamento";
        public static final String MUNICIPIO = "municipio";
    }

}
