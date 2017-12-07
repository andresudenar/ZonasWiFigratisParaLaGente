package isistemas.umariana.edu.co.zonaswifigratisparalagente;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetallesFragment extends Fragment {

    public String name, department, municipality, reg, dir, state;
    public TextView nombre, departamento, municipio, region, direccion, estado;
    View view;


    public DetallesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_detalles, container, false);

        nombre = (TextView) view.findViewById(R.id.name);
        departamento = (TextView) view.findViewById(R.id.departamento);
        municipio = (TextView) view.findViewById(R.id.municipio);
        region = (TextView) view.findViewById(R.id.region);
        direccion = (TextView) view.findViewById(R.id.direccion);
        estado = (TextView) view.findViewById(R.id.estado);

        //recuperar datos
        name = getArguments()!=null? getArguments().getString("name") : "";
        department = getArguments()!=null? getArguments().getString("departamento") : "";
        municipality = getArguments()!=null? getArguments().getString("municipio") : "";
        reg = getArguments()!=null? getArguments().getString("region") : "";
        dir = getArguments()!=null? getArguments().getString("direccion") : "";
        state = getArguments()!=null? getArguments().getString("estado") : "";

        nombre.setText(name);
        departamento.setText(department);
        municipio.setText(municipality);
        region.setText(reg);
        direccion.setText(dir);
        estado.setText(state);

        return view;
    }

    /*public void cargarMapa(View view){


        boolean connected = false;

        ConnectivityManager connec = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Recupera todas las redes (tanto móviles como wifi)
        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (int i = 0; i < redes.length; i++) {
            // Si alguna red tiene conexión, se devuelve true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }

        if (connected == true){

            Intent inten = new Intent (getContext(),MapsActivity.class);
            startActivity(inten);

        }else {

            AlertDialog.Builder uBuilder = new AlertDialog.Builder(getActivity());
            View aView = getLayoutInflater().inflate(R.layout.oops, null);
            uBuilder.setView(aView);
            final AlertDialog dialog = uBuilder.create();
            dialog.show();

        }

    }*/
}
