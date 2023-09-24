package com.example.myblogapp.Activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Html.ImageGetter
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapp.Models.Blogs
import com.example.blogapp.RestApi.ApiUtils
import com.example.myblogapp.Adapter.RVAdapter
import com.example.myblogapp.R
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    var blogId: Int = 0

    private lateinit var acitivtyDetailTextBaslik: TextView
    private lateinit var acitivtyDetailTextAciklama: TextView
    private lateinit var activityDetailImageView: ImageView

    private lateinit var rv: RecyclerView
    private lateinit var rvAdapter: RVAdapter

    var kategoriID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        acitivtyDetailTextBaslik = findViewById(R.id.acitivtyDetailTextBaslik)
        acitivtyDetailTextAciklama = findViewById(R.id.acitivtyDetailTextAciklama)
        activityDetailImageView = findViewById(R.id.activityDetailImageView)
        rv = findViewById(R.id.rv)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

        blogId = intent.getIntExtra("blogId", 0)
        Log.i("gelenBlogId", blogId.toString())

        detailBlog()

    }


    fun detailBlog() {
        val kdi = ApiUtils.getPosts()

        kdi.blogDetayGetir(blogId).enqueue(object : Callback<Blogs> {
            override fun onResponse(call: Call<Blogs>?, response: Response<Blogs>?) {
                if (response != null) {
                    acitivtyDetailTextBaslik.text = response.body().BlogBaslik
                    acitivtyDetailTextAciklama.text =
                        Html.fromHtml(response.body().BlogText, Html.FROM_HTML_MODE_LEGACY)

                    //  acitivtyDetailTextAciklama.text = response.body().BlogText
                    Picasso.get()
                        .load("https://emree.com.tr/android/blogapp_resimler/" + response.body().BlogGenisResim)
                        .into(activityDetailImageView)

                    Log.i("gelenBenzerID", response.body().KategoriID.toString())

                    //kategoriID = response.body().KategoriID

                    benzerBlogGetir(response.body().KategoriID)
                    //benzerBlogGetir(response.body().KategoriID)
                }
            }

            override fun onFailure(call: Call<Blogs>?, t: Throwable?) {
                TODO("Not yet implemented")
            }
        })
    }

    fun benzerBlogGetir(kategoriIDDegeri: Int) {
        val kdi = ApiUtils.getPosts()

        kdi.blogBenzerleri(kategoriIDDegeri).enqueue(object : Callback<List<Blogs>> {
            override fun onResponse(call: Call<List<Blogs>>?, response: Response<List<Blogs>>?) {
                if (response != null) {
                    Log.i("gelenBenzerResponse", response.body().toString())
                    rvAdapter = RVAdapter(this@DetailActivity, response.body())
                    rv.adapter = rvAdapter
                }
            }

            override fun onFailure(call: Call<List<Blogs>>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

        })
    }
}
