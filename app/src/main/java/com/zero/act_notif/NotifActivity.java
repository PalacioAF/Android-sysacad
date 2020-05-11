package com.zero.act_notif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.gson.Gson;
import com.zero.R;
import com.zero.dialog.Dialog;
import com.zero.model.Estudiante;
import com.zero.model.Notificacion;
import com.zero.response.ResponseGetNotificacion;
import com.zero.retrofit.ApiRest;
import com.zero.retrofit.Utilities;
import com.zero.sesion_manager.SesionManager;

import java.util.ArrayList;
import java.util.List;

public class NotifActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NotifAdapter adapter;
    SesionManager sesionManager;
    ApiRest mAPIService;
    String token;
    EditText searchInput;
    CharSequence search = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Ocultar title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_notif);
        getSupportActionBar().hide();

        //Iniciar ProgressBar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.notif_spin);
        Sprite sprite = new DoubleBounce();
        progressBar.setIndeterminateDrawable(sprite);

        //Search
        searchInput = findViewById(R.id.notif_search_input);

        //Search evento de cambio de texto
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
                search = s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        recyclerView = findViewById(R.id.notif__rv);
        sesionManager = new SesionManager(getApplicationContext());
        Estudiante estudiante = new Estudiante();
        estudiante = sesionManager.GetEstudiante();
        token = sesionManager.GetToken();
        Log.i("Prueba Rest", token);
        mAPIService = Utilities.getAPIService();

        mAPIService.GetNotificacion(token.toString(), estudiante.get_id()).enqueue(new Callback<ResponseGetNotificacion>() {
            @Override
            public void onResponse(Call<ResponseGetNotificacion> call, Response<ResponseGetNotificacion> response) {
                if (response.isSuccessful()) {
                    Gson objetoConsola = new Gson();
                    List<Notificacion> mdata = new ArrayList<Notificacion>();
                    Log.i("Prueba Rest", objetoConsola.toJson(response.body()));
                    for (Notificacion objeto : response.body().getNotificacion()) {
                        mdata.add(new Notificacion(objeto.get_id(),objeto.getTitulo(),objeto.getDescripcion(),objeto.getFechaCreacion()
                                ,objeto.getFechaActivacion(),objeto.getEstado(),objeto.getIdEstudiante()));
                        Log.i("Prueba Rest", objetoConsola.toJson(objeto.getTitulo()).toString());
                    }
                    adapter = new NotifAdapter(getApplicationContext(), mdata);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    progressBar.setVisibility(View.INVISIBLE);
                } else if (response.code() == 401){
                    progressBar.setVisibility(View.INVISIBLE);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    Dialog dialog =Dialog.newInstance("authentication");;
                    dialog.show(fragmentManager, "Alert");
                }else {
                    progressBar.setVisibility(View.INVISIBLE);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    Dialog dialog =Dialog.newInstance("api");;
                    dialog.show(fragmentManager, "Alert");
                }
            }

            @Override
            public void onFailure(Call<ResponseGetNotificacion> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                FragmentManager fragmentManager = getSupportFragmentManager();
                Dialog dialog =Dialog.newInstance("api");;
                dialog.show(fragmentManager, "Alert");
            }
        });

    }
}
