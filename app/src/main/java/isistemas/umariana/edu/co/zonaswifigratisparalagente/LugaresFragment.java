package isistemas.umariana.edu.co.zonaswifigratisparalagente;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import isistemas.umariana.edu.co.zonaswifigratisparalagente.models.ZonasWiFi;
import isistemas.umariana.edu.co.zonaswifigratisparalagente.models.ZonasWiFiSQLite;
import isistemas.umariana.edu.co.zonaswifigratisparalagente.utilidades.ServicioDbHelper;
import isistemas.umariana.edu.co.zonaswifigratisparalagente.utilidades.ZonasWifiAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class LugaresFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<ZonasWiFiSQLite> zonas;
    private ServicioDbHelper servicioDbHelper;
    private FragmentActivity myContext;
    private Spinner spinnerDepartamento, spinnerMunicipio;
    private ArrayList lista = new ArrayList();
    private View  view;
    private String optionD, optionM;


    public void onAttach(Activity activity){
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }


    public LugaresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lugares, container, false);

        recyclerView = view.findViewById(R.id.recyclador);
        recyclerView.setLayoutManager(new LinearLayoutManager(myContext));
        servicioDbHelper = new ServicioDbHelper(myContext);

        //cargarCardView("0");

        //Agregando datos a Spinner departamentos
        spinnerDepartamentos(view);
        //spinnerMunicipios("0");

        return view;

    }

    public void cargarCardView(String option){

        if(option == "1"){
            lista = servicioDbHelper.getZonasFDList(optionD);
            Log.i("prueba------>", option);
            //spinnerMunicipios(optionD);
        }else if(option == "2"){
            lista = servicioDbHelper.getZonasFMList(optionD,optionM);
            Log.i("prueba------>", option);
        }else if(option == "0"){
            lista =servicioDbHelper.getServiceList();
            Log.i("prueba------>", option);
            //spinnerMunicipios(optionD);
        }

        adapter = new ZonasWifiAdapter(lista);
        recyclerView.setAdapter(adapter);
    }

    public void spinnerDepartamentos(View view){

        spinnerDepartamento = (Spinner) view.findViewById(R.id.spinnerDepartamentos);
        final ArrayList departamentos = new ArrayList();
        departamentos.add(getResources().getString(R.string.departamento));
        ArrayList departamentosList =servicioDbHelper.getDepartamentosList();
        for(int i=0; i < departamentosList.size(); i++){
            ZonasWiFiSQLite d = (ZonasWiFiSQLite) departamentosList.get(i);
            departamentos.add(d.getItem());
        }
        spinnerDepartamento.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, departamentos));
        //spinnerDepartamento.setSelection(5);
        spinnerDepartamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                int positionDepartamento = spinnerDepartamento.getSelectedItemPosition();

                optionD = spinnerDepartamento.getItemAtPosition(spinnerDepartamento.getSelectedItemPosition()).toString();
                //Log.i("prueba------>", n);
                if(optionD != getResources().getString(R.string.departamento)){
                    spinnerMunicipios(optionD);
                    //cargarCardView("1");
                }else if(optionD ==getResources().getString(R.string.departamento)){
                    spinnerMunicipios(optionD);
                    //cargarCardView("0");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }


        });

    }

    public void spinnerMunicipios(final String option){
        spinnerMunicipio = (Spinner) view.findViewById(R.id.spinnerMunicipios);
        final ArrayList municipios = new ArrayList();
        municipios.add(getResources().getString(R.string.municipio));
        ArrayList municipiosDList =servicioDbHelper.getMunicipiosDepartamentosList(option);
        for(int i=0; i < municipiosDList.size(); i++){
            ZonasWiFiSQLite m = (ZonasWiFiSQLite) municipiosDList.get(i);
            municipios.add(m.getItem());
            //Log.i("prueba------>", municipios.get(i).toString());
        }
        spinnerMunicipio.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, municipios));

            spinnerMunicipio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                    int positionMunicipio = spinnerMunicipio.getSelectedItemPosition();
                    optionM = spinnerMunicipio.getItemAtPosition(spinnerMunicipio.getSelectedItemPosition()).toString();
                    //Log.i("prueba------>", option);
                    if(optionM != getResources().getString(R.string.municipio)){
                        cargarCardView("2");
                    }else if(optionD == getResources().getString(R.string.departamento) && optionM==getResources().getString(R.string.municipio)) {
                        cargarCardView("0");
                    }else {
                        cargarCardView("1");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Another interface callback
                }


            });
    }

    public void sharedPreferences(){
    }

}
