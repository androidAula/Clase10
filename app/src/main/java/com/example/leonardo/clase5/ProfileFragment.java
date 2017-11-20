package com.example.leonardo.clase5;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.plus.PlusOneButton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    // TODO: Rename and change types of parameters
    private String nombre;
    private String mParam2;
    private PlusOneButton mPlusOneButton;

    private OnFragmentPerfilListener mListener;

    public ProfileFragment() {

    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        TextView textView=(TextView) view.findViewById(R.id.tv_nameUser);
        textView.setText(nombre);
        ConnectionDetector cd = new ConnectionDetector(getActivity());
        if (cd.isConnectingToInternet())
            getPersonOnline();
        else
            readPersonFromFile();

        return view;
    }



    private void getPersonOnline() {
        new JSONParse().execute();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentPerfilListener) {
            mListener = (OnFragmentPerfilListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentPerfilListener {
       void onGetPerfilSuccsses(String result);
    }
    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JsonParser jsonParser=new JsonParser();
            JSONObject json = jsonParser.getJSONFromUrl("http://www.mocky.io/v2/5a130c8b2c0000d01face89b");
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            try {

                writeToFile(json.toString());
                mListener.onGetPerfilSuccsses(json.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    private void writeToFile(String data)
    {
        try
        {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    getActivity().openFileOutput("Person.json", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e)
        {
            Log.e("Message:", "File write failed: " + e.toString());
        }
    }

    private void readPersonFromFile()
    {
        String fortune = " ";
        try
        {
            InputStream inputStream = getActivity().openFileInput("Person.json");
            if (inputStream != null)
            {
                InputStreamReader inputStreamReader = new InputStreamReader(
                        inputStream);
                BufferedReader bufferedReader = new BufferedReader(
                        inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                Log.v("Message:", "reading...");
                while ((receiveString = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                fortune = stringBuilder.toString();
                mListener.onGetPerfilSuccsses(fortune);
            }
        } catch (FileNotFoundException e)
        {
            Log.e("Message:", "File not found: " + e.toString());
        } catch (IOException e)
        {
            Log.e("Message:", "Can not read file: " + e.toString());
        }

    }

}
