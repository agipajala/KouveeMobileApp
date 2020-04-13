package com.kouvee.API.Interface;

import com.kouvee.API.Response;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiPegawai {

    @POST("pegawai/login")
    @FormUrlEncoded
    Call<Response> Login(@Field("NIP") String NIP,
                         @Field("kataSandi") String kataSandi);
}
