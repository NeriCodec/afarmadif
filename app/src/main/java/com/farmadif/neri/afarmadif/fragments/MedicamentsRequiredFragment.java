package com.farmadif.neri.afarmadif.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.farmadif.neri.afarmadif.R;

/**
 * Created by neri on 5/05/17.
 */

public class MedicamentsRequiredFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.medicaments_required_main, container, false);
    }
}
