package com.example.froylan.u6_pr03_serviceweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    EditText marca;
    Button btn_mostrar,btn_buscar;
    ArrayList<Autos> auto= new ArrayList<>();
    ArrayAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_buscar=(Button)findViewById(R.id.buscar);
        btn_mostrar=(Button)findViewById(R.id.mostrar);
        listView=(ListView)findViewById(R.id.list_autos);
        marca=(EditText)findViewById(R.id.edit_marca);


        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar();
            }
        });
        btn_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(marca.getText())){
                    marca.setError(String.format(getString(R.string.ingresaa),getString(R.string.marca_del_auto)));
                }else {
                    buscar();
                }
            }
        });


    }

    public void consultar(){
        AsyncHttpClient cliente=new AsyncHttpClient();
        String url= "http://192.168.43.113/android/prueba.php";
        cliente.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String resp=new String(responseBody);
                int id=0;
                String modelo="",color="",espe="",marc="",anio="";
                double costo=0;
                try{
                    auto.clear();
                    JSONArray jsonArray=new JSONArray(resp);
                    for(int x=0;x<jsonArray.length();x++){
                        id=jsonArray.getJSONObject(x).getInt("id");
                        modelo=jsonArray.getJSONObject(x).getString("modelo");
                        color=jsonArray.getJSONObject(x).getString("color");
                        marc=jsonArray.getJSONObject(x).getString("marca");
                        espe=jsonArray.getJSONObject(x).getString("especificaciones");
                        anio=jsonArray.getJSONObject(x).getString("anio");
                        costo=jsonArray.getJSONObject(x).getDouble("costo");
                        Autos coche=new Autos(id,marc,modelo,color,espe,anio,costo);
                        auto.add(coche);
                        Log.i("sii",auto.size()+"");
                    }
                    llenarList();
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("sii","trono");
            }
        });
    }
    public void buscar(){
        AsyncHttpClient client= new AsyncHttpClient();
        String url= "http://192.168.43.113/android/prueba.php";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String resp=new String(responseBody);
                int id=0;
                String modelo="",color="",espe="",marc="",anio="";
                double costo=0;
                try{
                    auto.clear();
                    JSONArray jsonArray=new JSONArray(resp);
                    for(int x=0;x<jsonArray.length();x++){
                        marc= jsonArray.getJSONObject(x).getString("marca");
                        if(marc.equals(marca.getText().toString())){
                            id=jsonArray.getJSONObject(x).getInt("id");
                            modelo=jsonArray.getJSONObject(x).getString("modelo");
                            color=jsonArray.getJSONObject(x).getString("color");
                            espe=jsonArray.getJSONObject(x).getString("especificaciones");
                            anio=jsonArray.getJSONObject(x).getString("anio");
                            costo=jsonArray.getJSONObject(x).getDouble("costo");
                            Autos coche=new Autos(id,marc,modelo,color,espe,anio,costo);
                            auto.add(coche);
                            Log.i("sii",auto.size()+"");

                        }
                    }
                    llenarList();
                }catch (JSONException e){
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    private void llenarList(){
        adapter= new ArrayAdapter<Autos>(this,android.R.layout.simple_list_item_1,auto);
        listView.setAdapter(adapter);
    }
}
