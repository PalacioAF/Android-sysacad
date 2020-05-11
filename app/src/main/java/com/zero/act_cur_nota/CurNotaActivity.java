package com.zero.act_cur_nota;

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
import com.zero.model.Cursado;
import com.zero.model.Estudiante;
import com.zero.response.ResponseGetCursado;
import com.zero.retrofit.ApiRest;
import com.zero.retrofit.Utilities;
import com.zero.sesion_manager.SesionManager;

import java.util.ArrayList;
import java.util.List;

public class CurNotaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CurNotaAdapter adapter;
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
        setContentView(R.layout.activity_cur_nota);
        getSupportActionBar().hide();

        //Iniciar ProgressBar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.cur_nota_spin);
        Sprite sprite = new DoubleBounce();
        progressBar.setIndeterminateDrawable(sprite);

        //Search
        searchInput = findViewById(R.id.cur_nota_search_input);

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



        recyclerView = findViewById(R.id.cur_nota__rv);
        sesionManager = new SesionManager(getApplicationContext());
        Estudiante estudiante = new Estudiante();
        estudiante = sesionManager.GetEstudiante();
        token = sesionManager.GetToken();
        Log.i("Prueba Rest", token);
        mAPIService = Utilities.getAPIService();
        mAPIService.GetCursado(token.toString(), estudiante.get_id()).enqueue(new Callback<ResponseGetCursado>() {
            @Override
            public void onResponse(Call<ResponseGetCursado> call, Response<ResponseGetCursado> response) {
                if (response.isSuccessful()) {
                    Gson objetoConsola = new Gson();
                    List<Cursado> mdata = new ArrayList<Cursado>();
                    Log.i("Prueba Rest", objetoConsola.toJson(response.body()));
                    for (Cursado objeto : response.body().getCursado()) {
                        int img = ConverterImg(objeto.getAnio());
                        mdata.add(new Cursado(objeto.get_id(),objeto.getMateria(),objeto.getComision(),img,
                                objeto.getAula(),
                                objeto.getObs() ,
                                objeto.getIdEstudiante(),
                                objeto.getHorario(),
                                objeto.getNotaParciales()));
                        Log.i("Prueba Rest", objetoConsola.toJson(objeto.getMateria()).toString());
                    }
                    adapter = new CurNotaAdapter(CurNotaActivity.this, mdata);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(CurNotaActivity.this));

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
            public void onFailure(Call<ResponseGetCursado> call, Throwable t) {
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
