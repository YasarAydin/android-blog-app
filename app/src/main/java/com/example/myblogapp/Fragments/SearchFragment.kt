package com.example.myblogapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
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

class SearchFragment : Fragment() {

    private lateinit var editTextAra: EditText
    private lateinit var btnAra: Button
    private lateinit var rv: RecyclerView

    private lateinit var rvAdapter: RVAdapter

    var blogBaslik: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tasarim = inflater.inflate(R.layout.fragment_search, container, false)

        editTextAra = tasarim.findViewById(R.id.editTextAra)
        btnAra = tasarim.findViewById(R.id.btnAra)
        rv = tasarim.findViewById(R.id.rv)

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(context)

        blogBaslik = editTextAra.text.toString()

        btnAra.setOnClickListener {
            blogAra()
            //Toast.makeText(context, editTextAra.text.toString(), Toast.LENGTH_SHORT).show()
        }

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        return tasarim
    }

    fun blogAra() {
        val kdi = ApiUtils.getPosts()

        kdi.blogAra(editTextAra.text.toString()).enqueue(object : Callback<List<Blogs>> {
            override fun onResponse(call: Call<List<Blogs>>?, response: Response<List<Blogs>>?) {
                if (response != null) {
                    rvAdapter = RVAdapter(context!!, response.body())
                    rv.adapter = rvAdapter

                    Log.i("gelenAraRes", response.body().toString())
                }
            }

            override fun onFailure(call: Call<List<Blogs>>?, t: Throwable?) {
                TODO("Not yet implemented")
            }

        })
    }

}