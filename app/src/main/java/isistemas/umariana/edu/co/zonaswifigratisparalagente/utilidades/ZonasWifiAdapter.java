package isistemas.umariana.edu.co.zonaswifigratisparalagente.utilidades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import isistemas.umariana.edu.co.zonaswifigratisparalagente.DetallesFragment;
import isistemas.umariana.edu.co.zonaswifigratisparalagente.Inicio;
import isistemas.umariana.edu.co.zonaswifigratisparalagente.MainActivity;
import isistemas.umariana.edu.co.zonaswifigratisparalagente.R;
import isistemas.umariana.edu.co.zonaswifigratisparalagente.models.ZonasWiFiSQLite;

/**
 * Created by felipe on 28/11/17.
 */

public class ZonasWifiAdapter extends RecyclerView.Adapter <ZonasWifiAdapter.adapterViewHolder> {
    private List<ZonasWiFiSQLite> items;

    public static Context c;

    public static class adapterViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre,departamento,municipio,direccion;
        public View v;

        public adapterViewHolder(View v) {
            super(v);
            c = v.getContext();
            nombre = (TextView) v.findViewById(R.id.nombre);
            departamento = (TextView) v.findViewById(R.id.departamento);
            municipio = (TextView) v.findViewById(R.id.municipio);
            direccion = (TextView) v.findViewById(R.id.direccion);
            this.v=v;
        }
    }

    public ZonasWifiAdapter(ArrayList<ZonasWiFiSQLite> items) {
        this.items = items;
    }

    @Override
    public adapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_zonas,parent,false);
        return new adapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final adapterViewHolder holder, final int position) {

        holder.nombre.setText(items.get(position).getNombre_zona_wifi());
        holder.departamento.setText(items.get(position).getDepartamento());
        holder.municipio.setText(items.get(position).getMunicipio());
        holder.direccion.setText(items.get(position).getDireccion());

        holder.v.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.i("prueba------>",items.get(position).getNombre_zona_wifi());
                /*
                Intent in = new Intent(holder.c, MainActivity.class);
                in.putExtra("condicion","1");
                holder.c.startActivity(in);*/

                //cargar fraqment
                Fragment fragment = null;
                Class fragmentClass = DetallesFragment.class;

                try{
                    fragment = (Fragment) fragmentClass.newInstance();
                }catch (Exception e){
                    e.printStackTrace();
                }

                Bundle enviar = new Bundle();
                enviar.putString("name", items.get(position).getNombre_zona_wifi().toString());
                enviar.putString("departamento", items.get(position).getDepartamento().toString());
                enviar.putString("municipio", items.get(position).getMunicipio().toString());
                enviar.putString("region", items.get(position).getRegion().toString());
                enviar.putString("direccion", items.get(position).getDireccion().toString());
                enviar.putString("estado", items.get(position).getZona_inaguraruda().toString());
                fragment.setArguments(enviar);

                FragmentManager fragmentManager=((FragmentActivity)c).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });
    }

    //private Activity context;
    public int getItemCount(){
        return items.size();
    }
}
