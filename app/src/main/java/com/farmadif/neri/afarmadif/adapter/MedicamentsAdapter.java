package com.farmadif.neri.afarmadif.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.farmadif.neri.afarmadif.R;
import com.farmadif.neri.afarmadif.model.MedicamentModel;

import java.util.ArrayList;


public class MedicamentsAdapter extends RecyclerView.Adapter<MedicamentsAdapter.MedicamentsViewHolder> {

    private ArrayList<MedicamentModel> medicamentModelArrayList;

    public MedicamentsAdapter(ArrayList<MedicamentModel> medicamentModelArrayList) {
        this.medicamentModelArrayList = medicamentModelArrayList;
    }

    @Override
    public MedicamentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.medicament_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new MedicamentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MedicamentsViewHolder holder, int position) {
        holder.showMedicamentNameTextView.setText(medicamentModelArrayList.get(position).getNombre_comercial());
        holder.showMedicamentQuantityTextView.setText("Cantidad: " + medicamentModelArrayList.get(position).getDosis());
    }

    @Override
    public int getItemCount() {
        return medicamentModelArrayList.size();
    }

    class MedicamentsViewHolder extends RecyclerView.ViewHolder {

        private TextView showMedicamentNameTextView;
        private TextView showMedicamentQuantityTextView;

        public MedicamentsViewHolder(View itemView) {
            super(itemView);
            showMedicamentNameTextView = (TextView) itemView.findViewById(R.id.tv_medicament_name);
            showMedicamentQuantityTextView = (TextView) itemView.findViewById(R.id.tv_quantity_medicament);
        }
    }
}
