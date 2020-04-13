package com.kouvee.API.Interface;

import com.kouvee.API.Response;
import com.kouvee.DAO.supplierDAO;

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

public interface ApiSupplier {

    @GET("supplier")
    Call<Response> getAll();

    @GET("supplier/softDelete")
    Call<Response> getSoftDelete();

    @GET("supplier/{cari}")
    Call<Response> getCustomer(@Path("cari") String cari);

    @POST("supplier")
    @FormUrlEncoded
    Call<Response> insertSupplier(@Field("namaSupplier") String namaSupplier,
                                  @Field("alamat") String alamat,
                                  @Field("noHp") String  noHp,
                                  @Field("idPegawaiLog") String NIP);

    @Headers({"Content-Type: application/json"})
    @PUT("supplier/update/{id}")
    Call<Response> updateSupplier(@Path("id") String idProduk,
                                @Body supplierDAO body);
    @FormUrlEncoded
    @POST("supplier/{id}")
    Call<Response> deleteSupplier(@Path("id") int idSupplier,
                                  @Field("idPegawaiLog") String NIP);
    @FormUrlEncoded
    @POST("supplier/{id}/restore")
    Call<Response> restoreSupplier(@Path("id") int idSupplier,
                                   @Field("idPegawaiLog") String NIP);

    @DELETE("supplier/{id}/permanen")
    Call<Response> deleteSupplierPermanen(@Path("id") int idSupplier);
}
