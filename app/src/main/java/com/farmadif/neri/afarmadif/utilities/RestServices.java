package com.farmadif.neri.afarmadif.utilities;


import com.farmadif.neri.afarmadif.model.ResponseHttp;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestServices {

    @POST("usuarios/login")
    Call<ResponseHttp> userLogin(
            @Query("usuario") String usuario,
            @Query("contrasenia") String contrasenia,
            @Query("token") String token);

}
