package com.zero.login;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.zero.MainActivity;
import com.zero.R;
import com.zero.dialog.Dialog;
import com.zero.model.Estudiante;
import com.zero.model.FbUser;
import com.zero.response.ResponseGetEstudiante;
import com.zero.retrofit.ApiRest;
import com.zero.retrofit.Utilities;
import com.zero.sesion_manager.SesionManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txt_user;
    EditText txt_pass;
    Button btnLogin;
    private ApiRest mAPIService;
    String token;
    SesionManager sesionManager;
    CardView cvLogin;
    LinearLayout l1,l2;
    Animation uptodown,downtoup;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Ocultar title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        //Iniciar ProgressBar
        progressBar = (ProgressBar) findViewById(R.id.login_spin);
        Sprite sprite = new DoubleBounce();
        progressBar.setIndeterminateDrawable(sprite);


        //Animacion
        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);

        //Inicializo sesionManager para poder acceder a mis sharepreference
        sesionManager=new SesionManager(getApplicationContext());

        txt_user=(EditText) findViewById(R.id.login_txt_legajo);
        txt_pass=(EditText) findViewById(R.id.login_txt_pass);
        cvLogin=(CardView) findViewById(R.id.login_cv);
        cvLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.bringToFront();

        //validacion de campo vacio
        if(isEmpty(txt_user,txt_pass)){
            progressBar.setVisibility(View.INVISIBLE);
            FragmentManager fragmentManager = getSupportFragmentManager();
            Dialog dialog =Dialog.newInstance("login_empty");;
            dialog.show(fragmentManager, "Alert");
            Clear();
            return;
        }

        switch (view.getId()){
        case R.id.login_cv : mAPIService= Utilities.getAPIService();
            Log.i("Prueba Login", "Legajo:"+txt_user.getText().toString()+"-Pass:"+txt_pass.getText().toString());
            mAPIService.Login(Integer.valueOf(txt_user.getText().toString()),txt_pass.getText().toString()).enqueue(new Callback<ResponseGetEstudiante>() {
                @Override
                public void onResponse(Call<ResponseGetEstudiante> call, Response<ResponseGetEstudiante> response) {
                    if (response.isSuccessful() && response.code() == 200) {
                    Gson objetoConsola = new Gson();
                    Log.i("Prueba Login", objetoConsola.toJson(response.body().getEstudiente()));
                    Log.i("Prueba Login", objetoConsola.toJson(response.body().getToken()));
                    Estudiante estudiante = new Estudiante();
                    for (Estudiante objeto : response.body().getEstudiente()) {
                        estudiante.set_id(objeto.get_id().toString());
                        estudiante.setNombre(objeto.getNombre().toString());
                        estudiante.setApellido(objeto.getApellido().toString());
                        estudiante.setLegajo(Integer.valueOf(objeto.getLegajo()));
                        estudiante.setPassword(objeto.getPassword().toString());
                        estudiante.setIdCarrera(objeto.getIdCarrera());
                    }
                    Log.i("Prueba Login", estudiante.toString());

                    //Crea el registro del usuario en la DB de firebase
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference();
                    DatabaseReference usersRef = ref.child("users").child(String.valueOf(estudiante.getLegajo()));
                    FbUser firebaseUser=new FbUser(String.valueOf(estudiante.getLegajo()), FirebaseInstanceId.getInstance().getToken());
                    usersRef.setValue(firebaseUser);
                    Log.i("FCM","Registro token");


                    //Guardamos el usuario y token de autentificacion de la api rest en la share Preference
                    String token=objetoConsola.toJson(response.body().getToken().toString());
                    sesionManager.RegisterEstudiante(estudiante,token);

                    progressBar.setVisibility(View.INVISIBLE);

                    //Pasamos a la MainActivity
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    }
                    else if (response.code() == 204) {
                        progressBar.setVisibility(View.INVISIBLE);
                        //si no puede hacerlo es porq es resultado es vacio y el usuario o pass son invalidos
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        Dialog dialog =Dialog.newInstance("login");;
                        dialog.show(fragmentManager, "Alert");
                        Clear();
                    }
                    else {
                        progressBar.setVisibility(View.INVISIBLE);
                        //se muestra cuando falla la conexion con la api
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        Dialog dialog =Dialog.newInstance("logi_api");;
                        dialog.show(fragmentManager, "Alert");
                        Clear();
                    }
                }

                @Override
                public void onFailure(Call<ResponseGetEstudiante> call, Throwable t) {
                    progressBar.setVisibility(View.INVISIBLE);
                    //se muestra cuando falla la conexion con la api
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    Dialog dialog =Dialog.newInstance("logi_api");;
                    dialog.show(fragmentManager, "Alert");
                    Clear();
                }
            });

            break;
        default: break;
        }
    }

    //Verifica Campos vacios

    private boolean isEmpty(EditText txt_user, EditText txt_pass) {
        if (txt_user.getText().toString().trim().length() > 0 || txt_pass.getText().toString().trim().length() > 0) {
        return false;
        }
        return true;
    }

    //limpia los textView
    void Clear(){
        txt_user.setText("");
        txt_pass.setText("");
        txt_user.requestFocus();
    }
}
