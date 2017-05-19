package com.farmadif.neri.afarmadif.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.farmadif.neri.afarmadif.MainActivity;
import com.farmadif.neri.afarmadif.R;
import com.farmadif.neri.afarmadif.adapter.MedicamentsAdapter;
import com.farmadif.neri.afarmadif.model.MedicamentModel;
import com.farmadif.neri.afarmadif.utilities.FirebaseReferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MedicamentsFragment extends Fragment implements MainActivity.OnSearchListener {

    private ProgressBar progressBar;

    private RecyclerView medicamentsRecyclerView;

    private RecyclerView.Adapter adapter;

    private RelativeLayout offlineRelativeLayout;

    public ArrayList<MedicamentModel> medicaments = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.medicaments_main, container, false);

        progressBar = (ProgressBar) parent.findViewById(R.id.progressBar);
        medicamentsRecyclerView = (RecyclerView) parent.findViewById(R.id.rv_medicaments);
        offlineRelativeLayout = (RelativeLayout) parent.findViewById(R.id.rl_offline);

        setupRecyclerView();

        if(isNetworkAvailable()) {
            showMedicaments();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference(FirebaseReferences.MEDICAMENTOS);

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    medicaments.clear();
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        MedicamentModel medicament = data.getValue(MedicamentModel.class);
                        medicaments.add(medicament);
                        Log.v("Datos: ", medicament.getNombre_comercial());
                    }
                    adapter.notifyDataSetChanged();
                    showMedicaments();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            showNetworkOffline();
        }

        return parent;
    }

    private void setupRecyclerView() {
        Context context = getContext();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        medicamentsRecyclerView.setLayoutManager(layoutManager);
        medicamentsRecyclerView.setHasFixedSize(true);
        adapter = new MedicamentsAdapter(medicaments);
        medicamentsRecyclerView.setAdapter(adapter);
    }

    private void showMedicaments() {
        medicamentsRecyclerView.setVisibility(View.VISIBLE);
        offlineRelativeLayout.setVisibility(View.GONE);
    }

    private void showNetworkOffline() {
        offlineRelativeLayout.setVisibility(View.VISIBLE);
        medicamentsRecyclerView.setVisibility(View.GONE);
    }

    private boolean isNetworkAvailable() {
        Context context = getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return (actNetInfo != null && actNetInfo.isConnected());
    }

    @Override
    public void searchMedicament(String medicament) {

        if(medicaments == null) {
            return;
        }

        RecyclerView.Adapter adapter;

        if(!medicament.isEmpty()) {
            ArrayList<MedicamentModel> medicamentArrayList = new ArrayList<>();
            // TODO: Realizar algoritmo de busqueda especializada para el nombre, compuesto, etc.
            for (int i = 0; i < medicaments.size(); i++) {
                if(medicament.trim().toLowerCase().equals(medicaments.get(i).getNombre_comercial().toLowerCase())) {
                    medicamentArrayList.add((medicaments.get(i)));
                }
            }
            adapter = new MedicamentsAdapter(medicamentArrayList);
        } else {
            adapter = new MedicamentsAdapter(medicaments);
        }

        medicamentsRecyclerView.setAdapter(adapter);

    }

}
