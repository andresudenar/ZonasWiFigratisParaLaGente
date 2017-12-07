package isistemas.umariana.edu.co.zonaswifigratisparalagente.utilidades;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import isistemas.umariana.edu.co.zonaswifigratisparalagente.models.ZonasWiFi;
import isistemas.umariana.edu.co.zonaswifigratisparalagente.models.ZonasWiFiSQLite;

import static isistemas.umariana.edu.co.zonaswifigratisparalagente.utilidades.Utilidades.ZonasWiFiEntry;
/**
 * Created by felipe on 28/11/17.
 */

public class ServicioDbHelper extends SQLiteOpenHelper{

    private static final int DATABESE_VERSION = 1;
    private static final String DATABESE_NAME = "zonas.db";

    public ServicioDbHelper(Context context) {
        super(context, DATABESE_NAME, null, DATABESE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + ZonasWiFiEntry.TABLA+ " ("
                + ZonasWiFiEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ZonasWiFiEntry.ID + " TEXT NOT NULL,"
                + ZonasWiFiEntry.NOMBRE + " TEXT NOT NULL,"
                + ZonasWiFiEntry.ESTADO + " TEXT NOT NULL,"
                + ZonasWiFiEntry.PROYECTO + " TEXT NOT NULL,"
                + ZonasWiFiEntry.DIR + " TEXT NOT NULL,"
                + ZonasWiFiEntry.LAT + " NUMBER NOT NULL,"
                + ZonasWiFiEntry.LON + " NUMBER NOT NULL,"
                + ZonasWiFiEntry.NUMERO + " INTEGER NOT NULL,"
                + ZonasWiFiEntry.REGION + " TEXT NOT NULL,"
                + ZonasWiFiEntry.DEPARTAMENTO + " TEXT NOT NULL,"
                + ZonasWiFiEntry.MUNICIPIO + " TEXT NOT NULL )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long saveZonas(ZonasWiFi datos) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(ZonasWiFiEntry.TABLA, null, datos.toContentValues());
    }

    /**
     * =============================================================================================
     * METODO: Procesa una consulta
     **/
    public Cursor generateQuery(String query) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(query + ZonasWiFiEntry.TABLA + " ORDER BY "+ ZonasWiFiEntry.ID + " ASC", null);
    }

    public void deleteDataBase(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(ZonasWiFiEntry.TABLA, null, null);
        sqLiteDatabase.close();
    }

    public ArrayList<ZonasWiFiSQLite> getServiceList() {
        Cursor cursor;
        ArrayList<ZonasWiFiSQLite> servicios = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ZonasWiFiEntry.TABLA + " ORDER BY "+ ZonasWiFiEntry.DEPARTAMENTO + "," + ZonasWiFiEntry.MUNICIPIO + " ASC", null);

        if (cursor.moveToFirst()){
            do{
                ZonasWiFiSQLite servicio = new ZonasWiFiSQLite(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getInt(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11)
                );

                servicios.add(servicio);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return servicios;
    }

    public ArrayList<ZonasWiFiSQLite> getZonasFDList(String option) {
        Cursor cursor;
        ArrayList<ZonasWiFiSQLite> zonas = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ZonasWiFiEntry.TABLA+" WHERE "+ZonasWiFiEntry.DEPARTAMENTO+" = '"+option+"' ORDER BY "+ ZonasWiFiEntry.DEPARTAMENTO + "," + ZonasWiFiEntry.MUNICIPIO + " ASC", null);

        if (cursor.moveToFirst()){
            do{
                ZonasWiFiSQLite zona = new ZonasWiFiSQLite(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getInt(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11)
                );

                zonas.add(zona);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return zonas;
    }

    public ArrayList<ZonasWiFiSQLite> getZonasFMList(String dep, String mun) {
        Cursor cursor;
        ArrayList<ZonasWiFiSQLite> zonas = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ZonasWiFiEntry.TABLA+" WHERE "+ZonasWiFiEntry.DEPARTAMENTO+" = '"+dep+"' AND "+ ZonasWiFiEntry.MUNICIPIO+" = '"+mun+"' ORDER BY "+ ZonasWiFiEntry.DEPARTAMENTO + "," + ZonasWiFiEntry.MUNICIPIO + " ASC", null);

        if (cursor.moveToFirst()){
            do{
                ZonasWiFiSQLite zona = new ZonasWiFiSQLite(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getInt(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11)
                );

                zonas.add(zona);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return zonas;
    }

    public ArrayList<ZonasWiFiSQLite> getDepartamentosList() {
        Cursor cursor;
        ArrayList<ZonasWiFiSQLite> departamentos = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT DISTINCT "+ ZonasWiFiEntry.DEPARTAMENTO+" FROM "+ZonasWiFiEntry.TABLA + " ORDER BY "+ ZonasWiFiEntry.DEPARTAMENTO + " ASC", null);

        if (cursor.moveToFirst()){
            do{
                ZonasWiFiSQLite d = new ZonasWiFiSQLite(
                        cursor.getString(0)
                );

                departamentos.add(d);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return departamentos;
    }

    public ArrayList<ZonasWiFiSQLite> getMunicipiosDepartamentosList(String departamento) {
        Cursor cursor;
        ArrayList<ZonasWiFiSQLite> departamentosMunicipios = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT DISTINCT "+ZonasWiFiEntry.MUNICIPIO+" FROM "+ZonasWiFiEntry.TABLA+" WHERE "+ ZonasWiFiEntry.DEPARTAMENTO +" = '"+departamento+"' ORDER BY "+ ZonasWiFiEntry.MUNICIPIO + " ASC", null);

        if (cursor.moveToFirst()){
            do{
                ZonasWiFiSQLite m = new ZonasWiFiSQLite(
                        cursor.getString(0)
                );

                departamentosMunicipios.add(m);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return departamentosMunicipios;
    }
}
