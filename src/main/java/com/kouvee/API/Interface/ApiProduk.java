package com.kouvee.API.Interface;

import com.kouvee.API.Response;
import com.kouvee.DAO.produkDAO;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiProduk {

    @GET("produk")
    Call<Response> getAll();

    @GET("produk/softDelete")
    Call<Response> getSoftDelete();

    @GET("produk/{cari}")
    Call<Response> getProduk(@Path("cari") String cari);

    @GET("produk/softDelete/{cari}")
    Call<Response> getProdukHapus(@Path("cari") String cari);

    @Multipart
    @POST("produk")
    Call<Response> insertProduk (@Part("namaProduk") RequestBody namaProduk,
                                 @Part("harga") RequestBody  harga,
                                 @Part("stok") RequestBody  stok,
                                 @Part("jumlahMinimal") RequestBody jumlahMinimal,
                                 @Part MultipartBody.Part gambar,
                                 @Part("idPegawaiLog") RequestBody  idPegawaiLog);

    @Multipart
    @POST("produk/update/{id}")
    Call<Response> editProduk (@Path("id") String idProduk,
                               @Part("namaProduk") RequestBody namaProduk,
                               @Part("harga") RequestBody  harga,
                               @Part("stok") RequestBody  stok,
                               @Part("jumlahMinimal") RequestBody jumlahMinimal,
                               @Part MultipartBody.Part gambar,
                               @Part("idPegawaiLog") RequestBody  idPegawaiLog);


    @FormUrlEncoded
    @POST("produk/{id}")
    Call<Response> deleteProduk(@Path("id") String idProduk,
                                @Field("idPegawaiLog") String NIP);

    @FormUrlEncoded
    @POST("produk/{id}/restore")
    Call<Response> restoreProduk(@Path("id") String idProduk,
                                 @Field("idPegawaiLog") String NIP);

    @DELETE("produk/{id}/permanen")
    Call<Response> deleteProdukPermanen(@Path("id") String idProduk);

}
