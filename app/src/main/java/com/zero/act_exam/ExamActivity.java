package com.zero.act_exam;

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
import com.zero.model.Examen;
import com.zero.response.ResponseGetExamen;
import com.zero.retrofit.ApiRest;
import com.zero.retrofit.Utilities;
import com.zero.sesion_manager.SesionManager;

import java.util.ArrayList;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ExamAdapter adapter;
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
        setContentView(R.layout.activity_exam);
        getSupportActionBar().hide();

        //Iniciar ProgressBar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.exam_spin);
        Sprite sprite = new DoubleBounce();
        progressBar.setIndeterminateDrawable(sprite);

        //Search
        searchInput = findViewById(R.id.exam_search_input);

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


        recyclerView = findViewById(R.id.exam__rv);
        sesionManager = new SesionManager(getApplicationContext());
        Estudiante estudiante = new Estudiante();
        estudiante = sesionManager.GetEstudiante();
        token = sesionManager.GetToken();
        Log.i("Prueba Rest", token);
        mAPIService = Utilities.getAPIService();

        mAPIService.GetExamenes(token.toString(), estudiante.get_id()).enqueue(new Callback<ResponseGetExamen>() {
            @Override
            public void onResponse(Call<ResponseGetExamen> call, Response<ResponseGetExamen> response) {
                if (response.isSuccessful()) {
                    Gson objetoConsola = new Gson();
                    List<Examen> mdata = new ArrayList<Examen>();
                    Log.i("Prueba Rest", objetoConsola.toJson(response.body()));
                    for (Examen objeto : response.body().getExamenes()) {
                        int img = ConverterImg(objeto.getNota());
                        mdata.add(new Examen(objeto.get_id(), objeto.getMateria(), objeto.getFecha(), objeto.getNota(), img, objeto.getIdEstudiante()));
                        Log.i("Prueba Rest", objetoConsola.toJson(objeto.getMateria()).toString());
                    }
                    adapter = new ExamAdapter(getApplicationContext(), mdata);
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
            public void onFailure(Call<ResponseGetExamen> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                FragmentManager fragmentManager = getSupportFragmentManager();
                Dialog dialog =Dialog.newInstance("api");;
                dialog.show(fragmentManager, "Alert");
            }
        });


    }

    public int ConverterImg(String i) {
        int img;
        switch (i) {
            case "uno":
                img = R.drawable.e_one;
                break;
            case "dos":
                img = R.drawable.e_two;
                break;
            case "tres":
                img = R.drawable.e_three;
                break;
            case "cuatro":
                img = R.drawable.e_four;
                break;
            case "cinco":
                img = R.drawable.e_five;
                break;
            case "seis":
                img = R.drawable.e_six;
                break;
            case "siete":
                img = R.drawable.e_seven;
                break;
            case "ocho":
                img = R.drawable.e_eight;
                break;
            case "nueve":
                img = R.drawable.e_nine;
                break;
            case "diez":
                img = R.drawable.e_ten;
                break;
            default:
                img = R.drawable.e_null;
                break;
        }
        return img;
    }
}
