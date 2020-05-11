package com.zero.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zero.R;
import com.zero.login.LoginActivity;

import androidx.fragment.app.DialogFragment;

public class Dialog extends DialogFragment {

    public Dialog() {
    }

    public static Dialog newInstance(String reason) {
        Dialog frag = new Dialog();
        Bundle args = new Bundle();
        args.putString("Reason", reason);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        final String reason = getArguments().getString("Reason");
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog,null);
        builder.setView(view);
        android.app.Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btn=(Button) view.findViewById(R.id.dialog_btn);
        final TextView title=(TextView) view.findViewById(R.id.dialog_title);
        final TextView detail=(TextView) view.findViewById(R.id.dialog_detail);

        title.setText("Información");

        //Texto
        switch (reason){
            case "login":
                detail.setText("Legajo y/o Password Incorrectos");
                break;
            case "logi_api":
                detail.setText("Hubo un error en la conexion");
                break;
            case "login_empty":
                detail.setText("El campo Legajo y/o Password estan vacio");
                break;
            case "api":
                detail.setText("Hubo un error en la conexion");
                break;
            case "authentication":
                detail.setText("Problemas con la autenticación del token, por favor vuelva a loguearse");
                break;
        }

        //evento
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (reason){
                    case "login":
                        dismiss();
                        break;
                    case "logi_api":
                        dismiss();
                        break;
                    case "login_empty":
                        dismiss();
                        break;
                    case "api":
                        getActivity().finish();
                        break;
                    case "authentication":
                        //sesionManager.logout();
                        Intent intent=new Intent(getActivity(), LoginActivity.class);
                        intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        getActivity().startActivity(intent);
                        //getActivity().finish();
                        break;
                }
            }
        });


        dialog.setCanceledOnTouchOutside(false);
        return dialog;

    }
}
