package com.example.myblogapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapp.RestApi.ApiUtils
import com.example.myblogapp.Adapter.CategoriesAdapter
import com.example.myblogapp.Models.CategoriesList
import com.example.myblogapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesFragment : Fragment() {

    private lateinit var categoriesRV: RecyclerView
    private lateinit var categoryAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tasarim = inflater.inflate(R.layout.fragment_categories, container, false)

        categoriesRV = tasarim.findViewById(R.id.categoriesRV)
        categoriesRV.setHasFixedSize(true)
       // categoriesRV.layoutManager = LinearLayoutManager(context)
        val layoutManager = GridLayoutManager(context, 2)
        categoriesRV.layoutManager = layoutManager

        kategoriListele()

        return tasarim
    }

    fun kategoriListele() {
        val kdi = ApiUtils.getPosts()
        kdi.kategoriListele().enqueue(object : Callback<List<CategoriesList>> {
            override fun onResponse(
                call: Call<List<CategoriesList>>?,
                response: Response<List<CategoriesList>>?
            ) {
                if (response != null) {
                    Log.i("gelenResponseKategori", response.body().get(0).KategoriID.toString())
                    categoryAdapter = CategoriesAdapter(context!!, response.body())
                    categoriesRV.adapter = categoryAdapter
                }
            }

            override fun onFailure(call: Call<List<CategoriesList>>?, t: Throwable?) {
                TODO("Not yet implemented")
            }
        })
    }

}