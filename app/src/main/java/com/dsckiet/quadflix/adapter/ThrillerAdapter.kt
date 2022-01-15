package com.dsckiet.quadflix.adapter

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dsckiet.quadflix.R
import com.dsckiet.quadflix.model.MoviesItem


class ThrillerAdapter(private val context: Context): RecyclerView.Adapter<ThrillerAdapter.FeedViewHolder>() {
    private var collect:List<MoviesItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return FeedViewHolder(v)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val collect= collect[position]
        if(!collect.show.name.isNullOrEmpty()){ holder.title.text = collect.show.name }
        if(!collect.show.summary.isNullOrEmpty()){
            val text: String = collect.show.summary
            val styledText: Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
            } else {
                TODO("VERSION.SDK_INT < N")
            }
            holder.summary.text =  styledText

        }
        if(collect.show.image != null){ Glide.with(holder.itemView).load(collect.show.image.original).into(holder.moiveimage) }
        holder.itemView.setOnClickListener {
            val bundle = Bundle()

            if(collect.show.name!=null){ bundle.putString("title", collect.show.name) }
            else{
                bundle.putString("title", "null")
            }
            if(collect.show.summary!=null){ bundle.putString("summary", collect.show.summary) }
            else{
                bundle.putString("summary", "null")
            }
            if(collect.show.image!=null){ bundle.putString("image", collect.show.image.original) }
            else{
                bundle.putString("image","null")
            }
            if(collect.show.url!=null){ bundle.putString("url", collect.show.url) }
            else{
                bundle.putString("url", "null")
            }
            if(collect.score!=null){ bundle.putDouble("score", collect.score) }
            else{
                bundle.putDouble("score", 0.0)
            }
            if(collect.show.language!=null){ bundle.putString("language", collect.show.language) }
            else{
                bundle.putString("language","null")
            }
            if( collect.show.network!=null && collect.show.network.name!=null ){ bundle.putString("networkname", collect.show.network.name) }
            else {
                bundle.putString("networkname","null")
            }
            holder.itemView.findNavController().navigate(R.id.action_homeFragment_to_detailsFragment,bundle)
        }

    }

    override fun getItemCount(): Int {
        return collect.size
    }

    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val moiveimage = itemView.findViewById<ImageView>(R.id.movieimg)
        val title = itemView.findViewById<TextView>(R.id.movietitle)
        val summary = itemView.findViewById<TextView>(R.id.moviesumm)
    }

    fun setStateWiseTracker(list: List<MoviesItem>){
        this.collect=list
        notifyDataSetChanged()
    }
}
