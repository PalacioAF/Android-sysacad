package com.zero.act_cur_nota;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zero.R;
import com.zero.model.Cursado;
import com.zero.model.Horario;
import com.zero.model.NotaParciales;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CurNotaAdapter extends RecyclerView.Adapter<CurNotaAdapter.MainViewHolder> implements Filterable {

    Context mContext;
    List<Cursado> mData;
    List<Cursado> mDataFiltered;
    Dialog detail;

    public CurNotaAdapter(Context mContext, List<Cursado> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFiltered = mData;
    }


    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View layaut;
        layaut = LayoutInflater.from(mContext).inflate(R.layout.activity_cur_nota_item, parent, false);
        final MainViewHolder viewHolder = new MainViewHolder(layaut);

        //Dialog
        detail = new Dialog(mContext);
        detail.setContentView(R.layout.activity_cur_nota_item_detail);
        detail.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        viewHolder.container.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView detail_horario = (TextView) detail.findViewById(R.id.cur_nota_txt_horario);
                TextView detail_nota = (TextView) detail.findViewById(R.id.cur_nota_txt_notas);
                TextView detail_obs = (TextView) detail.findViewById(R.id.cur_nota_txt_obs);
                TextView detail_comision = (TextView) detail.findViewById(R.id.cur_nota_img_comision);
                detail_horario.setText((mDataFiltered.get(viewHolder.getAdapterPosition()).getHorario() == null) ? "-" : mDataFiltered.get(viewHolder.getAdapterPosition()).ToStringCursado());
                detail_nota.setText((mDataFiltered.get(viewHolder.getAdapterPosition()).getNotaParciales() == null) ? "-" : mDataFiltered.get(viewHolder.getAdapterPosition()).ToStringNota());
                detail_obs.setText((mDataFiltered.get(viewHolder.getAdapterPosition()).getObs() == null) ? "-" : mDataFiltered.get(viewHolder.getAdapterPosition()).getObs());
                detail_comision.setText(mDataFiltered.get(viewHolder.getAdapterPosition()).getComision());


                detail.show();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.img_nivel.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_transition_animation));
        holder.container.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation));

        holder.txt_materia.setText(mDataFiltered.get(position).getMateria());
        holder.txt_aula.setText(mDataFiltered.get(position).getAula());
        holder.txt_comision.setText(mDataFiltered.get(position).getComision());
        holder.img_nivel.setImageResource(mDataFiltered.get(position).getAnio());
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
                    mDataFiltered = mData;
                } else {
                    List<Cursado> lstFiltered = new ArrayList<>();
                    for (Cursado row : mData) {

                        if (row.getMateria().toLowerCase().contains(Key.toLowerCase())) {
                            lstFiltered.add(row);
                        }
                    }
                    mDataFiltered = lstFiltered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDataFiltered = (List<Cursado>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView txt_materia, txt_aula, txt_comision;
        ImageView img_nivel;
        RelativeLayout container;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.cur_nota_container);
            txt_materia = itemView.findViewById(R.id.cur_nota_txt_materia);
            txt_aula = itemView.findViewById(R.id.cur_nota_txt_aula);
            txt_comision = itemView.findViewById(R.id.cur_nota_txt_comision);
            img_nivel = itemView.findViewById(R.id.cur_nota_img_nivel);
        }
    }

}
