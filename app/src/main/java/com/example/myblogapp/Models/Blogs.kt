package com.example.blogapp.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Blogs (
    @SerializedName("BlogID")
    @Expose
    var BlogID: Int,

    @SerializedName("BlogBaslik")
    @Expose
    var BlogBaslik: String,

    @SerializedName("BlogText")
    @Expose
    var BlogText: String,

    @SerializedName("BlogKucukResim")
    @Expose
    var BlogKucukResim: String,

    @SerializedName("BlogGenisResim")
    @Expose
    var BlogGenisResim: String,

    @SerializedName("BlogTarih")
    @Expose
    var BlogTarih: String,

    @SerializedName("BlogYazar")
    @Expose
    var BlogYazar: String,

    @SerializedName("BlogKategori")
    @Expose
    var BlogKategori: Int,

    @SerializedName("KategoriID")
    @Expose
    var KategoriID: Int,

    @SerializedName("KategoriAd")
    @Expose
    var KategoriAd: String,


        ) {
}