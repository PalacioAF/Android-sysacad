package com.zero.act_mat_plan;

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
import com.zero.model.MateriasPlan;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MatPlanAdapter extends RecyclerView.Adapter<MatPlanAdapter.MainViewHolder> implements Filterable {

    Context mContext;
    List<MateriasPlan> mData;
    List<MateriasPlan> mDataFiltered ;

    public MatPlanAdapter(Context mContext, List<MateriasPlan> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFiltered = mData;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layaut;
        layaut = LayoutInflater.from(mContext).inflate(R.layout.activity_mat_plan_item,parent,false);
        return new MainViewHolder(layaut);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.img_nivel.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));
        holder.container.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));


        holder.txt_materia.setText(mDataFiltered.get(position).getMateria());
        holder.txt_dictado.setText(mDataFiltered.get(position).getDic());
        holder.img_nivel.setImageResource(mDataFiltered.get(position).getNivel());

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
                    List<MateriasPlan> lstFiltered = new ArrayList<>();
                    for (MateriasPlan row : mData) {
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
                mDataFiltered = (List<MateriasPlan>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MainViewHolder extends  RecyclerView.ViewHolder{
        TextView txt_materia,txt_dictado;
        ImageView img_nivel;
        RelativeLayout container;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            container=itemView.findViewById(R.id.mat_plan_container);
            txt_materia=itemView.findViewById(R.id.mat_plan_txt_materia);
            txt_dictado=itemView.findViewById(R.id.mat_plan_txt_dictado);
            img_nivel=itemView.findViewById(R.id.mat_plan_img_nivel);

        }
    }
}
