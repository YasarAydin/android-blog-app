package com.example.blogapp.RestApi

class ApiUtils {

    companion object {

        val BASE_URL = "http://emree.com.tr/android/"

        fun getPosts(): RAinterface {
            return RetrofitClient.getClient(BASE_URL).create(RAinterface::class.java)
        }

    }

}