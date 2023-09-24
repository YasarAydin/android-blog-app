package com.example.myblogapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapp.Models.Blogs
import com.example.blogapp.RestApi.ApiUtils
import com.example.myblogapp.Adapter.RVAdapter
import com.example.myblogapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class BlogwCategoryActivity : AppCompatActivity() {

    var kategoriID = 0

    private lateinit var textViewKategoriAdWCategort: TextView
    private lateinit var recyclerViewWCategory: RecyclerView
    private lateinit var rvAdapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blogw_category)

        textViewKategoriAdWCategort = findViewById(R.id.textViewKategoriAdWCategort)

        recyclerViewWCategory = findViewById(R.id.recyclerViewWCategory)
        recyclerViewWCategory.setHasFixedSize(true)
        recyclerViewWCategory.layoutManager = LinearLayoutManager(this@BlogwCategoryActivity)

        kategoriID = intent.getIntExtra("KategoriID", 0)

        blogGetirwKategori()
    }

    fun blogGetirwKategori() {
        val kdi = ApiUtils.getPosts()
        kdi.blogGetirwKategori(kategoriID).enqueue(object : Callback<List<Blogs>> {
            override fun onResponse(call: Call<List<Blogs>>?, response: Response<List<Blogs>>?) {
                if (response != null && response.body().isNotEmpty()) {
                    val txt = response.body().get(0).KategoriAd + " Kategorisinde " + response.body().size + " İçerik Bulundu"
                    textViewKategoriAdWCategort.text = txt
                    //textViewKategoriAdWCategort.text = response.body().get(kategoriID).KategoriAd

                    rvAdapter = RVAdapter(this@BlogwCategoryActivity, response.body())
                    recyclerViewWCategory.adapter = rvAdapter
                } else {
                    var intent = Intent(this@BlogwCategoryActivity, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(
                        this@BlogwCategoryActivity, "Bu Kategoride Bir İçerik Bulunamadı",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Blogs>>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

        })
    }
}