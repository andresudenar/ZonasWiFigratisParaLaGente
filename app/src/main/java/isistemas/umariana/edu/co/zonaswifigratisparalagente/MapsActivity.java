package isistemas.umariana.edu.co.zonaswifigratisparalagente;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import isistemas.umariana.edu.co.zonaswifigratisparalagente.datosApi.SitiosService;
import isistemas.umariana.edu.co.zonaswifigratisparalagente.models.ZonasWiFi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Retrofit retrofit;
    LatLng coordenada,colombia,zona;
    String latitud;
    private List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if (status == ConnectionResult.SUCCESS){
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }else{
            errorConexion();
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        retrofit = new Retrofit.Builder().baseUrl("http://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SitiosService service = retrofit.create(SitiosService.class);
        Call<List<ZonasWiFi>> sitioRespuestaCall = service.obtenerListadeZonas();

        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setAllGesturesEnabled(true);

        //verificando geolocalización
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
        }

        // Add a marker in Sydney and move the camera
        colombia = new LatLng(4.0000000, -72.0000000);
        float zoomLevel = 5;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(colombia, zoomLevel));

        sitioRespuestaCall.enqueue(new Callback<List<ZonasWiFi>>() {
            @Override
            public void onResponse(Call<List<ZonasWiFi>> call, Response<List<ZonasWiFi>> response) {
                list = response.body();
                for(int i=0; i < list.size(); i++){
                    ZonasWiFi m = (ZonasWiFi) list.get(i);

                    if(m.getLatitud() != null && m.getLongitud() != null) {

                        double latitud = Double.parseDouble(m.getLatitud());
                        double longitud = Double.parseDouble(m.getLongitud());
                        String nombreZonaWifi = (m.getNombreZonaWifi());
                        String direccion = (m.getDireccion());
                        String municipio = (m.getMunicipio());

                        coordenada = new LatLng(latitud, longitud);

                        mMap.addMarker(new MarkerOptions().position(coordenada).title(municipio+" "+nombreZonaWifi).snippet("Dirección : "+ direccion)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ZonasWiFi>> call, Throwable t) {

            }
        });
    }

    public  void errorConexion(){
        AlertDialog.Builder uBuilder = new AlertDialog.Builder(MapsActivity.this);
        View aView = getLayoutInflater().inflate(R.layout.oops, null);
        uBuilder.setView(aView);
        final AlertDialog dialog = uBuilder.create();
        dialog.show();
    }
}
