package isistemas.umariana.edu.co.zonaswifigratisparalagente;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import java.util.List;

import isistemas.umariana.edu.co.zonaswifigratisparalagente.datosApi.Comunicar;
import isistemas.umariana.edu.co.zonaswifigratisparalagente.datosApi.SitiosService;
import isistemas.umariana.edu.co.zonaswifigratisparalagente.models.ZonasWiFi;

import isistemas.umariana.edu.co.zonaswifigratisparalagente.utilidades.ServicioDbHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private Retrofit retrofit;
    public static final String TAG = "zonasWiFi";
    public ServicioDbHelper servicioDbHelper;
    public NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        servicioDbHelper = new ServicioDbHelper(this);

        retrofit = new Retrofit.Builder().baseUrl("http://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.inicio);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarInicio();
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Aquí

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_inicio);

        cargarInicio();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            obtenerDatos();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        Fragment fragment = null;
        Class fragmentClass = Inicio.class;

        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            // Handle the camera action
            fragmentClass = Inicio.class;
            setTitle(getResources().getString(R.string.inicio));
        } else if (id == R.id.nav_lugares) {
            fragmentClass = LugaresFragment.class;
            setTitle(getResources().getString(R.string.lugares));
        } else if (id == R.id.nav_mapa) {
            cargarMapa();
        } else if (id == R.id.nav_manage) {
            setTitle(getResources().getString(R.string.acercaDe));
            fragmentClass = AcercaDeFragment.class;
        } else if (id == R.id.nav_actualizar) {
            setTitle(getResources().getString(R.string.inicio));
            obtenerDatos();
        } else if (id == R.id.nav_salir) {
            finish();
        }

        try{
            fragment = (Fragment) fragmentClass.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent,fragment).commit();

        Log.i("prueba------>","AQUI 5");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void obtenerDatos(){
        SitiosService service = retrofit.create(SitiosService.class);
        Call<List<ZonasWiFi>> sitioRespuestaCall = service.obtenerListadeZonas();

        sitioRespuestaCall.enqueue(new Callback<List<ZonasWiFi>>() {

            @Override
            public void onResponse(Call<List<ZonasWiFi>> call, Response<List<ZonasWiFi>> response) {

                if (response.isSuccessful()) {
                    servicioDbHelper.deleteDataBase();

                    List<ZonasWiFi> lista = response.body();
                    for(int i=0; i<lista.size(); i++){

                        servicioDbHelper.saveZonas(lista.get(i));
                    }

                    Cursor cursor = servicioDbHelper.generateQuery("SELECT * FROM ");
                    Log.e("TAMAÑO DB",cursor.getCount()+"");

                }

                else{
                    Log.e(TAG, "Onresponse: "+ response.errorBody());
                    //errorConexion();
                }



            }

            @Override
            public void onFailure(Call<List<ZonasWiFi>> call, Throwable t) {

                Log.e(TAG, " OnFaillure: "+ t.getMessage());
                errorConexion();

            }
        });
    }

    public void cargarMapa(){

        navigationView.setCheckedItem(R.id.nav_inicio);

        boolean connected = false;

        ConnectivityManager connec = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Recupera todas las redes (tanto móviles como wifi)
        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (int i = 0; i < redes.length; i++) {
            // Si alguna red tiene conexión, se devuelve true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }

        if (connected == true){

            Intent inten = new Intent (this,MapsActivity.class);
            setTitle(getResources().getString(R.string.inicio));
            startActivity(inten);

        }else {

            AlertDialog.Builder uBuilder = new AlertDialog.Builder(MainActivity.this);
            View aView = getLayoutInflater().inflate(R.layout.oops, null);
            uBuilder.setView(aView);
            final AlertDialog dialog = uBuilder.create();
            dialog.show();

        }

    }

    public  void errorConexion(){
        navigationView.setCheckedItem(R.id.nav_inicio);
        AlertDialog.Builder uBuilder = new AlertDialog.Builder(MainActivity.this);
        View aView = getLayoutInflater().inflate(R.layout.oops, null);
        uBuilder.setView(aView);
        final AlertDialog dialog = uBuilder.create();
        dialog.show();
    }

    public void cargarInicio(){
        navigationView.setCheckedItem(R.id.nav_inicio);
        Fragment fragment = null;
        Class fragmentClass = Inicio.class;

        try{
            fragment = (Fragment) fragmentClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        setTitle(getResources().getString(R.string.inicio));
    }
}
