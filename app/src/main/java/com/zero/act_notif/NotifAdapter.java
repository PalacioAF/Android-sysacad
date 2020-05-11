package com.zero.act_notif;

import android.content.Context;
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
        return new MainViewHolder(layaut);
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
