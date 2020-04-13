package com.kouvee.API.Interface;

import com.kouvee.API.Response;
import com.kouvee.DAO.layananDAO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiLayanan {

    @GET("layanan")
    Call<Response> getAll();

    @GET("layanan/{cari}")
    Call<Response> getLayanan(@Path("cari") String cari);

    @POST("layanan")
    @FormUrlEncoded
    Call<Response> insertLayanan(@Field("namaLayanan")String namaLayanan,
                                 @Field("harga")Double harga,
                                 @Field("idUkuran")int idUkuran,
                                 @Field("idPegawaiLog") String NIP);

    @Headers({"Content-Type: application/json"})
    @PUT("layanan/update/{id}")
    Call<Response> editLayanan(@Path("id") int idCustomer,
                             @Body layananDAO body);
    @FormUrlEncoded
    @POST("layanan/{id}")
    Call<Response> deleteLayanan(@Path("id") String idLayanan,
                                 @Field("idPegawaiLog") String NIP);
    @FormUrlEncoded
    @POST("layanan/{id}/restore")
    Call<Response> restoreLayanan(@Path("id") String idLayanan,
                                  @Field("idPegawaiLog") String NIP);
    @DELETE("layanan/{id}/permanen")
    Call<Response> deleteLayananPermanen(@Path("id") String idLayanan);
}
