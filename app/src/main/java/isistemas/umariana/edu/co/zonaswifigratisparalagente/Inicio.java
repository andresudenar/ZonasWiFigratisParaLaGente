package isistemas.umariana.edu.co.zonaswifigratisparalagente;


import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import java.util.ArrayList;
import java.util.List;

import isistemas.umariana.edu.co.zonaswifigratisparalagente.models.ZonasWiFi;
import isistemas.umariana.edu.co.zonaswifigratisparalagente.models.ZonasWiFiSQLite;
import isistemas.umariana.edu.co.zonaswifigratisparalagente.utilidades.ServicioDbHelper;
import isistemas.umariana.edu.co.zonaswifigratisparalagente.utilidades.ZonasWifiAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class Inicio extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    //private ZonasWifiAdapter adapter;
    private ArrayList<ZonasWiFiSQLite> zonas;
    private ServicioDbHelper servicioDbHelper;
    private FragmentActivity myContext;
    private boolean bandera = false;
    String arrayName[]={
            "facebook",
            "google",
            "home"
    };

    public void onAttach(Activity activity){
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_inicio, container, false);

        return view;
    }

}
