package com.kouvee.API.Interface;

import com.kouvee.API.Response;
import com.kouvee.DAO.jenisDAO;
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

public interface ApiJenis {
    @GET("jenisHewan")
    Call<Response> getAll();

    @GET("jenisHewan/softDelete")
    Call<Response> getSoftDelete();

    @GET("jenisHewan/{cari}")
    Call<Response> getJenis(@Path("cari") String cari);

    @POST("jenisHewan")
    @FormUrlEncoded
    Call<Response> insertJenis(@Field("namaJenis")String namaJenis,
                               @Field("idPegawaiLog") String NIP);

    @Headers({"Content-Type: application/json"})
    @PUT("jenisHewan/update/{id}")
    Call<Response> editHewan(@Path("id") int idCustomer,
                             @Body jenisDAO body);
    @FormUrlEncoded
    @POST("jenisHewan/{id}")
    Call<Response> deleteJenis(@Path("id") int idJenis,
                               @Field("idPegawaiLog") String NIP);
    @FormUrlEncoded
    @POST("jenisHewan/{id}/restore")
    Call<Response> restoreJenis(@Path("id") int idJenis,
                                @Field("idPegawaiLog") String NIP);

    @DELETE("jenisHewan/{id}/permanen")
    Call<Response> deleteJenisPermanen(@Path("id") int idJenis);
}
