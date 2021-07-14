package com.example.beautybell.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beautybell.R
import com.example.beautybell.data.model.Artisan
import com.example.beautybell.databinding.ActivityHomeBinding
import com.example.beautybell.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        liveDataObserver()

        viewModel.fetchArtisanList()

    }

    private fun liveDataObserver() {
        viewModel.artisalListLiveData.observe(this, Observer {
//            hideLoading()
//            MainActivity.startActivity(this)
            Toast.makeText(this, "data : ", Toast.LENGTH_SHORT).show()
            val linearLayoutManager = LinearLayoutManager(this)
            binding.rvArtisan.layoutManager = linearLayoutManager
            binding.rvArtisan.adapter = ArtisanListAdapter(it)
        })

        viewModel.artisalLiveData.observe(this, Observer {
//            hideLoading()
//            MainActivity.startActivity(this)
            Toast.makeText(this, "data : ${it.name}", Toast.LENGTH_SHORT).show()
        })

        viewModel.errorArtisanListLiveData.observe(this, Observer {
//            hideLoading()
//            showMessage(it.message)
        })
    }
}