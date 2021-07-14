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

class ArtisanListAdapter(private var a: MutableList<Artisan>) :
    RecyclerView.Adapter<ArtisanListAdapter.ViewHolder>() {

    private var context: Context? = null
    private var artisanList = a

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val clParent = view.findViewById(R.id.clParent) as ConstraintLayout
        val tvName = view.findViewById(R.id.tvName) as TextView
        val tvDescription = view.findViewById(R.id.tvDescription) as TextView
        val ivProfile = view.findViewById(R.id.ivProfile) as ImageView

        init {
            // Define click listener for the ViewHolder's View.
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_artisan_single_item, viewGroup, false)

        context = viewGroup.context

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvName.text = artisanList[position].name
        viewHolder.tvDescription.text = artisanList[position].description
        Glide.with(context!!).load(artisanList[position].avatar).apply(RequestOptions().centerCrop()).into(viewHolder.ivProfile)

        viewHolder.clParent.setOnClickListener {
            ArtisanActivity.startActivity(context!!, artisanList[position].id!!)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = artisanList.size

    fun updateList(artisans: MutableList<Artisan>) {
        artisanList = artisans
        notifyDataSetChanged()
    }
}