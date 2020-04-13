package com.kouvee.API.Interface;

import com.kouvee.API.Response;
import com.kouvee.DAO.customerDAO;

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

public interface ApiCustomer {

    @GET("customer")
    Call<Response> getAll();

    @GET("customer/softDelete")
    Call<Response> getSoftDelete();

    @GET("customer/{cari}")
    Call<Response> getCustomer(@Path("cari") String cari);

    @POST("customer")
    @FormUrlEncoded
    Call<Response> insertCustomer(@Field("namaCustomer") String namaCustomer,
                                  @Field("alamat") String alamat,
                                  @Field("tglLahir") Date tglLahir,
                                  @Field("noHp") String  noHp,
                                  @Field("idPegawaiLog") String NIP);

    @Headers({"Content-Type: application/json"})
    @PUT("customer/update/{id}")
    Call<Response> editCustomer(@Path("id") int idCustomer,
                                @Body customerDAO body);
    @FormUrlEncoded
    @POST("customer/{id}")
    Call<Response> deleteCustomer(@Path("id") int idCustomer,
                                  @Field("idPegawaiLog") String NIP);
    @FormUrlEncoded
    @POST("customer/{id}/restore")
    Call<Response> restoreCustomer(@Path("id") int idCustomer,
                                   @Field("idPegawaiLog") String NIP);

    @DELETE("customer/{id}/permanen")
    Call<Response> deleteCustomerPermanen(@Path("id") int idCustomer);
}
