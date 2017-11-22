package com.example.leonardo.clase5;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.leonardo.clase5.Utils.ClientesHelper;

/**
 * Created by Leonardo on 22/11/2017.
 */

public class FragmentSql extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_sql,container,false);

        final EditText nombre=(EditText) view.findViewById(R.id.nombre);
        final EditText telefono=(EditText) view.findViewById(R.id.telefono);
        final EditText correo=(EditText) view.findViewById(R.id.correo);
        Button button=(Button) view.findViewById(R.id.guardar_cliente);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String BD_NOMBRE = "DBClientes";
                int BD_VERSION = 1;
                ClientesHelper clidbh = new ClientesHelper(
                        getContext(), BD_NOMBRE, null, BD_VERSION);
                SQLiteDatabase db = clidbh.getWritableDatabase();
                db.execSQL("INSERT INTO Clientes (nombre, telefono, email) " +
                        "VALUES ('" + nombre.getText() + "', '" + telefono.getText() +"', '" + correo.getText() + "')");
                db.close();
            }
        });

        return view;
    }
}
