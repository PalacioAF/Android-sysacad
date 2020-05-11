package com.zero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zero.act_corr_cur.CorrCurActivity;
import com.zero.act_corr_exa.CorrExaActivity;
import com.zero.act_cur_nota.CurNotaActivity;
import com.zero.act_cur_nota.CurNotaAdapter;
import com.zero.act_est_acad.EstAcadActivity;
import com.zero.act_exam.ExamActivity;
import com.zero.act_mat_plan.MatPlanActivity;
import com.zero.act_notif.NotifActivity;
import com.zero.model.Estudiante;
import com.zero.retrofit.ApiRest;
import com.zero.sesion_manager.SesionManager;

//Subido a github
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txt_estudiante;
    TextView txt_Carrera;
    SesionManager sesionManager;
    private ApiRest mAPIService;
    private long lastClickTime = 0;

    private CardView cvEstAcad,cvMatPlan,cvExam,cvCurNota,cvCorrCur,cvCorrExa,cvNotf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //definicion de objetos
        txt_estudiante=(TextView) findViewById(R.id.main_txt_Estudiante);
        txt_Carrera=(TextView) findViewById(R.id.main_txt_Carrera);
        cvEstAcad=(CardView) findViewById(R.id.main_cv_est_acad);
        cvMatPlan=(CardView) findViewById(R.id.main_cv_mat_plan);
        cvExam=(CardView) findViewById(R.id.main_cv_exam);
        cvCurNota=(CardView) findViewById(R.id.main_cv_cur_nota);
        cvCorrCur=(CardView) findViewById(R.id.main_cv_corr_cur);
        cvCorrExa=(CardView) findViewById(R.id.main_cv_corr_exa);
        cvNotf=(CardView) findViewById(R.id.main_cv_notif);

        //obtengo los datos del estudiante de las share preference
        sesionManager=new SesionManager(getApplicationContext());
        Estudiante estudiante=new Estudiante();
        estudiante=sesionManager.GetEstudiante();

        //asigno el nombre, apellido y carrera
        txt_estudiante.setText(estudiante.getApellido()+", "+estudiante.getNombre());
        txt_Carrera.setText(estudiante.getIdCarrera().getDescripcion());

        cvMatPlan.setOnClickListener(this);
        cvEstAcad.setOnClickListener(this);
        cvExam.setOnClickListener(this);
        cvCurNota.setOnClickListener(this);
        cvCorrCur.setOnClickListener(this);
        cvCorrExa.setOnClickListener(this);
        cvNotf.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //Previene en doble click
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();

        Intent intent;
        switch (view.getId()){
            case R.id.main_cv_mat_plan: intent=new Intent(this, MatPlanActivity.class); startActivity(intent);break;
            case R.id.main_cv_est_acad: intent=new Intent(this, EstAcadActivity.class); startActivity(intent);break;
            case R.id.main_cv_exam: intent=new Intent(this, ExamActivity.class); startActivity(intent);break;
            case R.id.main_cv_cur_nota: intent=new Intent(this, CurNotaActivity.class); startActivity(intent);break;
            case R.id.main_cv_corr_cur: intent=new Intent(this, CorrCurActivity.class); startActivity(intent);break;
            case R.id.main_cv_corr_exa: intent=new Intent(this, CorrExaActivity.class); startActivity(intent);break;
            case R.id.main_cv_notif: intent=new Intent(this, NotifActivity.class); startActivity(intent);break;
            default: break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //La app esta en primer plano (foreground).
        sesionManager.setState(true);
        Log.i("State","true");

    }
    @Override
    protected void onPause() {
        super.onPause();
        //La app esta en segundo plano (background).
        sesionManager.setState(false);
        Log.i("State","false");

    }
}
