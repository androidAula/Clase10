package com.example.leonardo.clase5;

import android.Manifest;
import android.support.v4.app.Fragment;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Leonardo on 22/11/2017.
 */

public class FragmentCalls extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calls, container,false);
        TextView txtResultados=(TextView) view.findViewById(R.id.tv_resultados);

        String[] projection = new String[]{
                CallLog.Calls.TYPE,
                CallLog.Calls.NUMBER};

        Uri llamadasUri = CallLog.Calls.CONTENT_URI;

        ContentResolver cr = getActivity().getContentResolver();

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }else {
            Cursor cur = cr.query(llamadasUri,
                    projection, //Columnas a devolver
                    null,       //Condición de la query
                    null,       //Argumentos variables de la query
                    null);      //Orden de los resultados

            if (cur.moveToFirst()) {
                int tipo;
                String tipoLlamada = "";
                String telefono;

                int colTipo = cur.getColumnIndex(CallLog.Calls.TYPE);
                int colTelefono = cur.getColumnIndex(CallLog.Calls.NUMBER);

                txtResultados.setText("");

                do {
                    tipo = cur.getInt(colTipo);
                    telefono = cur.getString(colTelefono);

                    if (tipo == CallLog.Calls.INCOMING_TYPE)
                        tipoLlamada = "ENTRADA";
                    else if (tipo == CallLog.Calls.OUTGOING_TYPE)
                        tipoLlamada = "SALIDA";
                    else if (tipo == CallLog.Calls.MISSED_TYPE)
                        tipoLlamada = "PERDIDA";

                    txtResultados.append(tipoLlamada + " - " + telefono + "\n");

                } while (cur.moveToNext());
            }
        }
        return view;
    }
}
