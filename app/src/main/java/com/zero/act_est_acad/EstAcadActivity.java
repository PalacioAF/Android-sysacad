package com.zero.act_est_acad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.os.SystemClock;
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
import com.zero.model.EstadoAcademico;
import com.zero.model.Estudiante;
import com.zero.response.ResponseGetEstadoAcademico;
import com.zero.retrofit.ApiRest;
import com.zero.retrofit.Utilities;
import com.zero.sesion_manager.SesionManager;

import java.util.ArrayList;
import java.util.List;

public class EstAcadActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EstAcadAdapter adapter;
    SesionManager sesionManager;
    ApiRest mAPIService;
    String token;
    EditText searchInput;
    CharSequence search="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Ocultar title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_est_acad);
        getSupportActionBar().hide();

        //Iniciar ProgressBar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.est_acad_spin);
        Sprite sprite = new DoubleBounce();
        progressBar.setIndeterminateDrawable(sprite);

        //Search
        searchInput = findViewById(R.id.est_acad_search_input);

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



        recyclerView = findViewById(R.id.est_acad_rv);
        sesionManager = new SesionManager(getApplicationContext());
        Estudiante estudiante = new Estudiante();
        estudiante = sesionManager.GetEstudiante();
        token = sesionManager.GetToken();
        Log.i("Prueba Rest", token);
        mAPIService = Utilities.getAPIService();
        mAPIService.GetEstadoAcademido(token.toString(), estudiante.get_id()).enqueue(new Callback<ResponseGetEstadoAcademico>() {
            @Override
            public void onResponse(Call<ResponseGetEstadoAcademico> call, Response<ResponseGetEstadoAcademico> response) {
                if (response.isSuccessful()) {
                    Gson objetoConsola = new Gson();
                    List<EstadoAcademico> mdata = new ArrayList<EstadoAcademico>();
                    Log.i("Prueba Rest", objetoConsola.toJson(response.body()));
                    for (EstadoAcademico objeto : response.body().getEstadoacademico()) {
                        int img = ConverterImg(Integer.valueOf(objeto.getAnio()));
                        mdata.add(new EstadoAcademico(objeto.get_id().toString(), objeto.getMateria().toString(),
                                ((objeto.getEstado() == null) ? "" : objeto.getEstado().toString())
                                , img,
                                objeto.getIdEstudiante().toString()));
                        Log.i("Prueba Rest", objetoConsola.toJson(objeto.getMateria()).toString());
                    }
                    adapter = new EstAcadAdapter(getApplicationContext(), mdata);
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
            public void onFailure(Call<ResponseGetEstadoAcademico> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                FragmentManager fragmentManager = getSupportFragmentManager();
                Dialog dialog =Dialog.newInstance("api");;
                dialog.show(fragmentManager, "Alert");
            }
        });


    }

    public int ConverterImg(int i) {
        int img;
        switch (i) {
            case 0:
                img = R.drawable.n_zero;
                break;
            case 1:
                img = R.drawable.n_one;
                break;
            case 2:
                img = R.drawable.n_two;
                break;
            case 3:
                img = R.drawable.n_three;
                break;
            case 4:
                img = R.drawable.n_four;
                break;
            case 5:
                img = R.drawable.n_five;
                break;
            default:
                img = 0;
                break;
        }
        return img;
    }

}
