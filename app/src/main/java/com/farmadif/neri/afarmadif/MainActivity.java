package com.farmadif.neri.afarmadif;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.farmadif.neri.afarmadif.fragments.MedicamentsFragment;
import com.farmadif.neri.afarmadif.fragments.MedicamentsRequiredFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    public OnSearchListener onSearchListener;


    public interface OnSearchListener {
        public void searchMedicament(String medicament);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_medicaments:
                    setupFragmentMedicaments();
                    return true;
                case R.id.navigation_medicaments_required:
                    setupFragmentMedicamentsRequired();
                    return true;
                case R.id.navigation_contact:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupFragment();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    private void setupFragment() {
        fragmentManager = getSupportFragmentManager();
    }

    private void setupFragmentMedicaments() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MedicamentsFragment medicamentsFragment = new MedicamentsFragment();

        fragmentTransaction.replace(R.id.content, medicamentsFragment);
        fragmentTransaction.commit();

    }

    private void setupFragmentMedicamentsRequired() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MedicamentsRequiredFragment medicamentsRequiredFragment = new MedicamentsRequiredFragment();

        fragmentTransaction.replace(R.id.content, medicamentsRequiredFragment);
        fragmentTransaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        final Context context = getBaseContext();
        ComponentName componentName = new ComponentName(context, MainActivity.class);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                onSearchListener.searchMedicament(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        try {
            onSearchListener = (OnSearchListener) fragment;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}
