package com.example.beautybell.view.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beautybell.R
import com.example.beautybell.data.model.Artisan
import com.example.beautybell.databinding.ActivityHomeBinding
import com.example.beautybell.viewmodel.ArtisanViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: ArtisanViewModel

    private var artisanListAdapter: ArtisanListAdapter? = null

    companion object {
        fun startActivity(sourceActivity: Context) {
            val intent = Intent(sourceActivity, HomeActivity::class.java)
            sourceActivity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        viewModel = ViewModelProviders.of(this).get(ArtisanViewModel::class.java)

        liveDataObserver()

        viewModel.fetchArtisanList()
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun liveDataObserver() {
        viewModel.artisalListLiveData.observe(this, Observer {
            binding.progressBar.visibility = View.GONE

            val artisanList = it
            artisanListAdapter = ArtisanListAdapter(artisanList)
            val linearLayoutManager = LinearLayoutManager(this)
            binding.rvArtisan.layoutManager = linearLayoutManager
            binding.rvArtisan.adapter = artisanListAdapter

            binding.etSearch.addTextChangedListener { b ->

                var newList: MutableList<Artisan>? = ArrayList()

                for(a: Artisan in artisanList) {
                    if (a.name?.contains(b.toString())!!) {
                        newList?.add(a)
                    }
                }
                if (newList != null) {
                    artisanListAdapter!!.updateList(newList)
                }
            }
        })

        viewModel.errorArtisanListLiveData.observe(this, Observer {
            binding.progressBar.visibility = View.GONE
            Toast.makeText(this, "Something Error", Toast.LENGTH_SHORT).show()
        })
    }
}