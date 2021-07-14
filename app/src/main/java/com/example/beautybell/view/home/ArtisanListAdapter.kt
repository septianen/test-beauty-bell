package com.example.beautybell.view.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.beautybell.R
import com.example.beautybell.data.model.Artisan
import com.example.beautybell.view.artisan.ArtisanActivity

class ArtisanListAdapter(private val a: MutableList<Artisan>) :
    RecyclerView.Adapter<ArtisanListAdapter.ViewHolder>() {

    private var context: Context? = null
    private var artisanList = a

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val clParent = view.findViewById(R.id.clParent) as ConstraintLayout
        val tvName = view.findViewById(R.id.tvName) as TextView
        val tvDescription = view.findViewById(R.id.tvDescription) as TextView
        val ivProfile = view.findViewById(R.id.ivProfile) as ImageView

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_artisan_single_item, viewGroup, false)

        context = viewGroup.context

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvName.text = artisanList[position].name
        viewHolder.tvDescription.text = artisanList[position].description
        Glide.with(context!!).load(artisanList[position].avatar).apply(RequestOptions().centerCrop()).into(viewHolder.ivProfile)

        viewHolder.clParent.setOnClickListener {
            ArtisanActivity.startActivity(context!!, artisanList[position].id!!)
        }
    }

    override fun getItemCount() = artisanList.size

    fun updateList(artisans: MutableList<Artisan>) {
        artisanList = artisans
        notifyDataSetChanged()
    }
}