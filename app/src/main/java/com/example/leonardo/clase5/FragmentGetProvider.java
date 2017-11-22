package com.example.leonardo.clase5;


import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leonardo.clase5.Utils.ClientesProvider;

/**
 * Created by Leonardo on 22/11/2017.
 */

public class FragmentGetProvider extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_getclients,container,false);
        TextView txtResultados=(TextView) view.findViewById(R.id.tv_clients);

        String[] projection = new String[] {
                ClientesProvider.Clientes._ID,
                ClientesProvider.Clientes.COL_NOMBRE,
                ClientesProvider.Clientes.COL_TELEFONO,
                ClientesProvider.Clientes.COL_EMAIL };

        Uri clientesUri =  ClientesProvider.CONTENT_URI;

        ContentResolver cr = getActivity().getContentResolver();

//Hacemos la consulta
        Cursor cur = cr.query(clientesUri,
                projection, //Columnas a devolver
                null,       //Condición de la query
                null,       //Argumentos variables de la query
                null);      //Orden de los resultados

        if (cur.moveToFirst())
        {
            String nombre;
            String telefono;
            String email;

            int colNombre = cur.getColumnIndex(ClientesProvider.Clientes.COL_NOMBRE);
            int colTelefono = cur.getColumnIndex(ClientesProvider.Clientes.COL_TELEFONO);
            int colEmail = cur.getColumnIndex(ClientesProvider.Clientes.COL_EMAIL);

            txtResultados.setText("");

            do
            {
                nombre = cur.getString(colNombre);
                telefono = cur.getString(colTelefono);
                email = cur.getString(colEmail);

                txtResultados.append(nombre + " - " + telefono + " - " + email + "\n");

            } while (cur.moveToNext());
        }
        return view;
    }
}
