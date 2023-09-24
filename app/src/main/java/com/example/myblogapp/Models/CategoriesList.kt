package com.example.myblogapp.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CategoriesList(
    @SerializedName("KategoriID")
    @Expose
    var KategoriID: Int,

    @SerializedName("KategoriAd")
    @Expose
    var KategoriAd: String,

    @SerializedName("KategoriResim")
    @Expose
    var KategoriResim: String,

    @SerializedName("KategoriAciklama")
    @Expose
    var KategoriAciklama: String
) {
}