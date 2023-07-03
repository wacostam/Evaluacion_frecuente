package com.example.actividad_nueva;

import static com.example.actividad_nueva.Modelos.Usuario.JsonObjectsBuild;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.actividad_nueva.Adaptadores.AdaptadorUsuario;
import com.example.actividad_nueva.Modelos.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {
    ListView lstOpciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstOpciones = (ListView) findViewById(R.id.lstListaUsuarios);
        View header = getLayoutInflater().inflate(R.layout.lyhearderusuario, null);
        lstOpciones.addHeaderView(header);
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://reqres.in/api/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONObject JSONlista = new JSONObject(result);
        JSONArray JSONlistaUsuarios= JSONlista.getJSONArray("data");
        ArrayList<Usuario>lstUsuarios= JsonObjectsBuild(JSONlistaUsuarios);
        AdaptadorUsuario adapatorUsuario = new AdaptadorUsuario(this, lstUsuarios);
        lstOpciones.setAdapter(adapatorUsuario);
    }
}