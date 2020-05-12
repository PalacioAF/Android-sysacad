package com.zero.act_notif;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zero.R;
import com.zero.model.CorrelatividadRendir;
import com.zero.model.Notificacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotifAdapter extends RecyclerView.Adapter<NotifAdapter.MainViewHolder> implements Filterable {

    Context mContext;
    List<Notificacion> mData;
    List<Notificacion> mDataFiltered ;
    Dialog detail;

    public NotifAdapter(Context mContext, List<Notificacion> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFiltered = mData;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layaut;
        layaut = LayoutInflater.from(mContext).inflate(R.layout.activity_notif_item,parent,false);
        final MainViewHolder viewHolder = new MainViewHolder(layaut);

        //Dialog
        detail = new Dialog(mContext);
        detail.setContentView(R.layout.activity_notif_item_detail);
        detail.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        viewHolder.container.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView titulo = (TextView) detail.findViewById(R.id.notif_popup_txt_title);
                TextView descripcion = (TextView) detail.findViewById(R.id.notif_popup_txt_detail);
                titulo.setText(mDataFiltered.get(viewHolder.getAdapterPosition()).getTitulo());
                descripcion.setText(mDataFiltered.get(viewHolder.getAdapterPosition()).getDescripcion());
                descripcion.setMovementMethod(new ScrollingMovementMethod());
                descripcion.scrollTo(0, 0);
                detail.show();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.img_estado.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));
        holder.container.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));

        holder.txt_titulo.setText(mDataFiltered.get(position).getTitulo());
        holder.txt_descripcion.setText(mDataFiltered.get(position).getDescripcion());
        holder.txt_fecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(mDataFiltered.get(position).getFechaCreacion()));
        holder.img_estado.setImageResource((mDataFiltered.get(position).getEstado()=="Activa"? R.drawable.notf_mail_open:R.drawable.notf_mail_new));
    }

    @Override
    public int getItemCount() {
        return mDataFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();
                if (Key.isEmpty()) {
                    mDataFiltered = mData ;
                }
                else {
                    List<Notificacion> lstFiltered = new ArrayList<>();
                    for (Notificacion row : mData) {

                        if (row.getTitulo().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);
                        }
                    }
                    mDataFiltered = lstFiltered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values= mDataFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDataFiltered = (List<Notificacion>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public class MainViewHolder extends  RecyclerView.ViewHolder{
        TextView txt_titulo,txt_descripcion,txt_fecha;
        ImageView img_estado;
        RelativeLayout container;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            container=itemView.findViewById(R.id.notif_container);
            txt_titulo=itemView.findViewById(R.id.notif_txt_titulo);
            txt_descripcion=itemView.findViewById(R.id.notif_txt_descripcion);
            txt_fecha=itemView.findViewById(R.id.notif_txt_fecha);
            img_estado=itemView.findViewById(R.id.notif_img_estado);

        }
    }
}
