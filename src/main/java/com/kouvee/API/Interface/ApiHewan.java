package com.kouvee.API.Interface;

import com.kouvee.API.Response;
import com.kouvee.DAO.hewanDAO;

import java.sql.Date;

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

public interface ApiHewan {
    @GET("hewan")
    Call<Response> getAll();

    @GET("hewan/softDelete")
    Call<Response> getSoftDelete();

    @GET("hewan/{cari}")
    Call<Response> getHewan(@Path("cari") String cari);

    @POST("hewan")
    @FormUrlEncoded
    Call<Response> insertHewan(@Field("namaHewan")String namaCustomer,
                               @Field("tglLahir") Date tglLahir,
                               @Field("idJenis") int idJenis,
                               @Field("idCustomer") int idCustomer,
                               @Field("idPegawaiLog") String NIP);

    @Headers({"Content-Type: application/json"})
    @PUT("hewan/update/{id}")
    Call<Response> editHewan(@Path("id") int idCustomer,
                                @Body hewanDAO body);
    @FormUrlEncoded
    @POST("hewan/{id}")
    Call<Response> deleteHewan(@Path("id") int idHewan,
                               @Field("idPegawaiLog") String NIP);
    @FormUrlEncoded
    @POST("hewan/{id}/restore")
    Call<Response> restoreHewan(@Path("id") int idHewan,
                                @Field("idPegawaiLog") String NIP);
    @DELETE("hewan/{id}/permanen")
    Call<Response> deleteHewanPermanen(@Path("id") int idHewan);
}
