package com.example.beautybell.view.artisan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.beautybell.R
import com.example.beautybell.data.model.Service

class ServiceListAdapter(private val serviceList: MutableList<Service>) :
    RecyclerView.Adapter<ServiceListAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val clParent = view.findViewById(R.id.clParent) as ConstraintLayout
        val tvName = view.findViewById(R.id.tvName) as TextView
        val tvPrice = view.findViewById(R.id.tvPrice) as TextView
        val tvCaption = view.findViewById(R.id.tvCaption) as TextView

        init {
            // Define click listener for the ViewHolder's View.
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_service_single_item, viewGroup, false)

        context = viewGroup.context

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvName.text = serviceList[position].name
        viewHolder.tvPrice.text = serviceList[position].price
        viewHolder.tvCaption.text = serviceList[position].caption
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = serviceList.size

}