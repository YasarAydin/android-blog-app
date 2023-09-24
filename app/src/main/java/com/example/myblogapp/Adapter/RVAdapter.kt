package com.example.myblogapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.blogapp.Models.Blogs
import com.example.myblogapp.Activities.DetailActivity
import com.example.myblogapp.R
import com.furkanakdemir.surroundcardview.SurroundCardView
import com.furkanakdemir.surroundcardview.SurroundListener
import com.squareup.picasso.Picasso

class RVAdapter(

    private val context: Context,
    private val bloglarList: List<Blogs>

) : RecyclerView.Adapter<RVAdapter.CardViewTasarimTutucu>() {
    inner class CardViewTasarimTutucu(view: View) : RecyclerView.ViewHolder(view) {
        var sampleSurroundCardView: SurroundCardView
        var cardViewSatirYaziTemsili: TextView
        var cardViewMainActivityCategoryName: TextView
       // var cardViewFavoriImageview: ImageView
        var cardViewImageView: ImageView

        init {
            sampleSurroundCardView = view.findViewById(R.id.sampleSurroundCardView)
            cardViewSatirYaziTemsili = view.findViewById(R.id.cardViewSatirYazi)
            //cardViewFavoriImageview = view.findViewById(R.id.cardViewFavoriImageview)
            cardViewImageView = view.findViewById(R.id.cardViewImageView)
            cardViewMainActivityCategoryName = view.findViewById(R.id.cardViewMainActivityCategoryName)

            sampleSurroundCardView.surround()
            sampleSurroundCardView.release()
            sampleSurroundCardView.switch()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewTasarimTutucu {
        val tasarim = LayoutInflater.from(context).inflate(R.layout.card_tasarim2, parent, false)
        return CardViewTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return bloglarList.size
    }

    override fun onBindViewHolder(holder: CardViewTasarimTutucu, position: Int) {
        val blog = bloglarList[position]
        holder.cardViewSatirYaziTemsili.text = blog.BlogBaslik
        holder.cardViewMainActivityCategoryName.text = blog.KategoriAd

        Picasso.get().load("https://emree.com.tr/android/blogapp_resimler/"+blog.BlogKucukResim).into(holder.cardViewImageView)

        holder.sampleSurroundCardView.setDuration(2000)
        holder.sampleSurroundCardView.setSurrounded(true)
        holder.sampleSurroundCardView.setSurroundStrokeWidth(com.google.android.material.R.dimen.m3_card_stroke_width)

        holder.sampleSurroundCardView.setOnClickListener {
            var intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("blogId", bloglarList[position].BlogID)
            context.startActivity(intent)
        }

        holder.sampleSurroundCardView.surroundListener = object : SurroundListener {
            override fun onSurround() {
                //Toast.makeText(context, "Ben onSurround'dan geliyurm", Toast.LENGTH_SHORT).show()
            }
        }

       /* holder.cardViewFavoriImageview.setOnClickListener {
            //Toast.makeText(context, "FAVORİ EKLENDİ", Toast.LENGTH_SHORT).show()
        }*/
    }


}