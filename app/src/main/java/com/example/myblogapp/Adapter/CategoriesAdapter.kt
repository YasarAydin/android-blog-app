package com.example.myblogapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myblogapp.Activities.BlogwCategoryActivity
import com.example.myblogapp.Models.CategoriesList
import com.example.myblogapp.R
import com.squareup.picasso.Picasso

class CategoriesAdapter(

    private val context: Context,
    private val categoriesList: List<CategoriesList>,


    ) : RecyclerView.Adapter<CategoriesAdapter.CardViewTasarimTutucu>() {
    inner class CardViewTasarimTutucu(view: View) : RecyclerView.ViewHolder(view) {

        var categoryImageView: ImageView
        var categoryBaslik: TextView

        init {
            categoryImageView = view.findViewById(R.id.categoryImageView)
            categoryBaslik = view.findViewById(R.id.categoryBaslik)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewTasarimTutucu {
        val tasarim =
            LayoutInflater.from(context).inflate(R.layout.card_tasarim_categories, parent, false)
        return CardViewTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CardViewTasarimTutucu, position: Int) {
        val categories = categoriesList[position]

        holder.categoryBaslik.text = categories.KategoriAd
         Picasso.get()
             .load("https://emree.com.tr/android/kategoriler_resimler/" + categories.KategoriResim)
             .into(holder.categoryImageView)

        holder.categoryImageView.setOnClickListener {
            var intent = Intent(context, BlogwCategoryActivity::class.java)
            intent.putExtra("KategoriID", categories.KategoriID)
            context.startActivity(intent)

        }

    }
}