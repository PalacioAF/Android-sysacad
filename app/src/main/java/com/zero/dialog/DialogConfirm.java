package com.zero.dialog;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zero.R;
import com.zero.login.LoginActivity;
import com.zero.model.Estudiante;
import com.zero.request.RequestDeleteNotificacion;
import com.zero.retrofit.ApiRest;
import com.zero.retrofit.Utilities;
import com.zero.sesion_manager.SesionManager;

import androidx.fragment.app.DialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogConfirm extends DialogFragment {
    SesionManager sesionManager;
    ApiRest mAPIService;

    public DialogConfirm() {
    }

    public static DialogConfirm newInstance(String reason) {
        DialogConfirm frag = new DialogConfirm();
        Bundle args = new Bundle();
        args.putString("Reason", reason);
        frag.setArguments(args);
        return frag;
    }

    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        final String reason = getArguments().getString("Reason");
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view;
        android.app.Dialog dialog;
        Button btn_ok, btn_cancel;
        final TextView title;
        final TextView detail;


        view = inflater.inflate(R.layout.dialog_confirm, null);
        builder.setView(view);
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btn_ok = (Button) view.findViewById(R.id.dialog_conf__btn_ok);
        btn_cancel = (Button) view.findViewById(R.id.dialog_conf__btn_cancel);
        title = (TextView) view.findViewById(R.id.dialog_conf_title);
        detail = (TextView) view.findViewById(R.id.dialog_conf_detail);
        title.setText("Información");

        //Texto
        switch (reason){
            case "delete":
                detail.setText("¿Está seguro de borrar las notificaciones?");
                break;
        }
        //evento
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (reason){
                    case "delete":
                        DeleteNotificacion();
                        dismiss();
                        getActivity().finish();
                        startActivity(getActivity().getIntent());
                        break;
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        return dialog;

    }

    public void DeleteNotificacion(){
        sesionManager = new SesionManager(getActivity());
        Estudiante estudiante = new Estudiante();
        estudiante = sesionManager.GetEstudiante();
        mAPIService= Utilities.getAPIService();
        mAPIService.DeleteNotificacion(estudiante.get_id()).enqueue(new Callback<RequestDeleteNotificacion>() {
            @Override
            public void onResponse(Call<RequestDeleteNotificacion> call, Response<RequestDeleteNotificacion> response) {
                Gson objetoConsola= new Gson();
                Log.i("Notificacion",objetoConsola.toJson(response.body()));
            }

            @Override
            public void onFailure(Call<RequestDeleteNotificacion> call, Throwable t) {

            }
        });
    }
}
