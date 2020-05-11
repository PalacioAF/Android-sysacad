package com.zero.act_corr_exa;

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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CorrExaAdapter extends RecyclerView.Adapter<CorrExaAdapter.MainViewHolder> implements Filterable {

    Context mContext;
    List<CorrelatividadRendir> mData;
    List<CorrelatividadRendir> mDataFiltered ;

    public CorrExaAdapter(Context mContext, List<CorrelatividadRendir> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFiltered = mData;
    }



    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layaut;
        layaut = LayoutInflater.from(mContext).inflate(R.layout.activity_corr_exa_item,parent,false);
        return new MainViewHolder(layaut);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.img_nota.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));
        holder.container.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));

        holder.txt_materia.setText(mDataFiltered.get(position).getMateria());
        holder.txt_correlatividad.setText((mDataFiltered.get(position).getCorrelatividad())== null ? "Puede Inscribirse" :mDataFiltered.get(position).ToStringCorrelatividad());
        holder.txt_plan.setText(mDataFiltered.get(position).getPlan());
        holder.img_nota.setImageResource(mDataFiltered.get(position).getAnio());
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
                    List<CorrelatividadRendir> lstFiltered = new ArrayList<>();
                    for (CorrelatividadRendir row : mData) {

                        if (row.getMateria().toLowerCase().contains(Key.toLowerCase())){
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
                mDataFiltered = (List<CorrelatividadRendir>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MainViewHolder extends  RecyclerView.ViewHolder{
        TextView txt_materia,txt_correlatividad,txt_plan;
        ImageView img_nota;
        RelativeLayout container;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            container=itemView.findViewById(R.id.corr_exa_container);
            txt_materia=itemView.findViewById(R.id.corr_exa_txt_materia);
            txt_correlatividad=itemView.findViewById(R.id.corr_exa_txt_correlatividad);
            txt_plan=itemView.findViewById(R.id.corr_exa_txt_plan);
            img_nota=itemView.findViewById(R.id.corr_exa_img_nivel);

        }
    }
}
