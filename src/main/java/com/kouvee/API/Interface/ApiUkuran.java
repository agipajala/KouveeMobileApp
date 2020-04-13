package com.kouvee.API.Interface;

import com.kouvee.API.Response;
import com.kouvee.DAO.ukuranDAO;

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

public interface ApiUkuran {
    @GET("ukuranHewan")
    Call<Response> getAll();

    @GET("ukuranHewan/softDelete")
    Call<Response> getSoftDelete();

    @GET("ukuranHewan/{cari}")
    Call<Response> getUkuran(@Path("cari") String cari);

    @POST("ukuranHewan")
    @FormUrlEncoded
    Call<Response> insertUkuran(@Field("namaUkuran")String namaUkuran,
                               @Field("idPegawaiLog") String NIP);

    @Headers({"Content-Type: application/json"})
    @PUT("ukuranHewan/update/{id}")
    Call<Response> updateUkuran(@Path("id") String idProduk,
                                @Body ukuranDAO body);
    @FormUrlEncoded
    @POST("ukuranHewan/{id}")
    Call<Response> deleteUkuran(@Path("id") int idUkuran,
                                @Field("idPegawaiLog") String NIP);
    @FormUrlEncoded
    @POST("ukuranHewan/{id}/restore")
    Call<Response> restoreUkuran(@Path("id") int idUkuran,
                                 @Field("idPegawaiLog") String NIP);

    @DELETE("ukuranHewan/{id}/permanen")
    Call<Response> deleteUkuranPermanen(@Path("id") int idUkuran);
}
