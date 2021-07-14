package com.example.beautybell.view.artisan

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.beautybell.R
import com.example.beautybell.databinding.ActivityArtisanBinding
import com.example.beautybell.view.home.ArtisanListAdapter
import com.example.beautybell.viewmodel.ArtisanViewModel

class ArtisanActivity : AppCompatActivity() {

    companion object {
        fun startActivity(sourceActivity: Context, id: String) {
            val intent = Intent(sourceActivity, ArtisanActivity::class.java)
            intent.putExtra("id", id)
            sourceActivity.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityArtisanBinding
    private lateinit var viewModel: ArtisanViewModel

    private var id: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artisan)

        try {
            id = intent?.getSerializableExtra("id") as String
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        viewModel = ViewModelProviders.of(this).get(ArtisanViewModel::class.java)

        liveDataObserver()

        viewModel.fetchArtisan(id?: "0")
    }

    private fun liveDataObserver() {
        viewModel.artisalLiveData.observe(this, Observer {
//            hideLoading()
//            MainActivity.startActivity(this)
            Toast.makeText(this, "data : ${it.services?.get(0)?.name}", Toast.LENGTH_SHORT).show()

            val linearLayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
            binding.rvService.layoutManager = linearLayoutManager
            binding.rvService.adapter = ServiceListAdapter(it.services!!)

            binding.tvName.text = it.name
            binding.tvDescription.text = it.description
            Glide.with(this).load(it.avatar).apply(RequestOptions().centerCrop()).into(binding.ivProfile)

        })

        viewModel.errorArtisanLiveData.observe(this, Observer {
//            hideLoading()
//            showMessage(it.message)
        })
    }
}