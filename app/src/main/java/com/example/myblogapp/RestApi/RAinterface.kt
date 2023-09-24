package com.example.blogapp.RestApi

import com.example.blogapp.Models.Blogs
import com.example.myblogapp.Models.CategoriesList
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RAinterface {

    /* @GET("posts")
     fun postsGetir(): Call<List<Posts>>

     @POST("posts/")
     @FormUrlEncoded
     fun blogDetay(@Field("blog_id") blog_id: Int): Call<Posts>*/

    @GET("blogs_list.php")
    fun blogGetir(): Call<List<Blogs>>

    @FormUrlEncoded
    @POST("blog_detail.php")
    fun blogDetayGetir(@Field("BlogID") BlogID: Int): Call<Blogs>

    @FormUrlEncoded
    @POST("blog_search.php")
    fun blogAra(@Field("blogBaslik") blogBaslik: String): Call<List<Blogs>>

    @FormUrlEncoded
    @POST("blog_benzerleri.php")
    fun blogBenzerleri(@Field("kategoriId") kategoriId: Int): Call<List<Blogs>>

    @FormUrlEncoded
    @POST("blog_list_with_categoryid.php")
    fun blogGetirwKategori(@Field("KategoriID") KategoriID: Int): Call<List<Blogs>>

    @GET("categories_list.php")
    fun kategoriListele(): Call<List<CategoriesList>>
}