package com.example.beautybell.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.beautybell.R
import com.example.beautybell.data.model.Artisan
import com.example.beautybell.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.fetchArtisanList()

        liveDataObserver()
    }

    private fun liveDataObserver() {
        viewModel.artisalListLiveData.observe(this, Observer {
//            hideLoading()
//            MainActivity.startActivity(this)
            Toast.makeText(this, "data : ${it.artisanList!![0].name}", Toast.LENGTH_SHORT).show()
        })

        viewModel.errorArtisanListLiveData.observe(this, Observer {
//            hideLoading()
//            showMessage(it.message)
        })
    }
}