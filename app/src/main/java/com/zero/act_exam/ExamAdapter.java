package com.zero.act_exam;

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
import com.zero.model.Examen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExamAdapter  extends RecyclerView.Adapter<ExamAdapter.MainViewHolder> implements Filterable {

    Context mContext;
    List<Examen> mData;
    List<Examen> mDataFiltered ;

    public ExamAdapter(Context mContext, List<Examen> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFiltered = mData;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layaut;
        layaut = LayoutInflater.from(mContext).inflate(R.layout.activity_exam_item,parent,false);
        return new MainViewHolder(layaut);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.img_nota.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));
        holder.container.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));

        holder.txt_materia.setText(mDataFiltered.get(position).getMateria());
        holder.txt_nota.setText(mDataFiltered.get(position).getNota());
        holder.txt_fecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(mDataFiltered.get(position).getFecha()));
        holder.img_nota.setImageResource(mDataFiltered.get(position).getN_Nota());
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
                    List<Examen> lstFiltered = new ArrayList<>();
                    for (Examen row : mData) {

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
                mDataFiltered = (List<Examen>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MainViewHolder extends  RecyclerView.ViewHolder{
        TextView txt_materia,txt_nota,txt_fecha;
        ImageView img_nota;
        RelativeLayout container;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            container=itemView.findViewById(R.id.exam_container);
            txt_materia=itemView.findViewById(R.id.exam_txt_materia);
            txt_fecha=itemView.findViewById(R.id.exam_txt_fecha);
            txt_nota=itemView.findViewById(R.id.exam_txt_nota);
            img_nota=itemView.findViewById(R.id.exam_img_nota);

        }
    }
}
