package com.example.beautybell.view.artisan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.beautybell.R
import com.example.beautybell.data.model.Service

class ServiceListAdapter(private val serviceList: MutableList<Service>) :
    RecyclerView.Adapter<ServiceListAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById(R.id.tvName) as TextView
        val tvPrice = view.findViewById(R.id.tvPrice) as TextView
        val tvCaption = view.findViewById(R.id.tvCaption) as TextView

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_service_single_item, viewGroup, false)

        context = viewGroup.context

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvName.text = serviceList[position].name
        viewHolder.tvPrice.text = serviceList[position].price
        viewHolder.tvCaption.text = serviceList[position].caption
    }

    override fun getItemCount() = serviceList.size

}