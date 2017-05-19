package com.farmadif.neri.afarmadif.utilities;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.farmadif.neri.afarmadif.SignInListener;
import com.farmadif.neri.afarmadif.model.ResponseHttp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Services {

    private static final String BASE_URL_REAL_DOMAIN = "http://192.168.1.70:8000/";

    private static final String token = "5c73ca7f-1ecf-417e-862c-7695614d18be";

    private final RestServices mRestServices;

    private SignInListener mListener;

    public Services(Context context) {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_REAL_DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRestServices = mRetrofit.create(RestServices.class);
        mListener = (SignInListener) context;
    }



    public void login(String usuario, String contrasenia, final ProgressBar loadingProgressBar) {

        Call<ResponseHttp> callback = mRestServices.userLogin(usuario, contrasenia, token);
        callback.enqueue(new Callback<ResponseHttp>() {

            @Override
            public void onResponse(Call<ResponseHttp> call, Response<ResponseHttp> response) {
                if(response.isSuccessful()) {
                    loadingProgressBar.setVisibility(View.GONE);
                    Log.v("Peticion:", response.message());
                    ResponseHttp responseHttp = response.body();
                    mListener.signInValidator(true);
                } else {
                    loadingProgressBar.setVisibility(View.GONE);
                    Log.v("Peticion:", response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseHttp> call, Throwable t) {

            }
        });
    }

}
