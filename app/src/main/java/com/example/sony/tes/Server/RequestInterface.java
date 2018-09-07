package com.example.sony.tes.Server;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by SONY on 4/9/2018.
 */
public interface RequestInterface {

    @GET("ihave/api/lesson/get_data?id_category=2")
    Call<JSONRespone> getJSON();


}
