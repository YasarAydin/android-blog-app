package com.example.myblogapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapp.Models.Blogs
import com.example.blogapp.RestApi.ApiUtils
import com.example.myblogapp.Adapter.RVAdapter
import com.example.myblogapp.R
import com.example.myblogapp.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var rv: RecyclerView
    private lateinit var rvAdapter: RVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tasarim = inflater.inflate(R.layout.fragment_home, container, false)

        rv = tasarim.findViewById(R.id.rv)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(context)

        blogGetir()
        return tasarim
    }

    fun blogGetir() {
        val kdi = ApiUtils.getPosts()

        kdi.blogGetir().enqueue(object : Callback<List<Blogs>> {
            override fun onResponse(
                call: Call<List<Blogs>>?,
                response: Response<List<Blogs>>?
            ) {
                if (response != null) {
                    // Log.i("gelenRes", response.body().get(0).BlogBaslik)
                    //rvAdapter = context?.let { RVAdapter(it.applicationContext, response.body()) }!!

                    rvAdapter = RVAdapter(context!!, response.body())
                    rv.adapter = rvAdapter
                }
            }

            override fun onFailure(call: Call<List<Blogs>>?, t: Throwable?) {
                TODO("Not yet implemented")
            }
        })
    }
}