package com.example.beautybell.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.beautybell.R
import com.example.beautybell.databinding.ActivityLoginBinding
import com.example.beautybell.view.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        when {
            binding.etUsername.text.toString().isEmpty() -> binding.etUsername.error = resources.getString(R.string.username_error)
            binding.etPassword.text.toString().isEmpty() -> binding.etPassword.error = resources.getString(R.string.password_error)
            else -> HomeActivity.startActivity(this)
        }
    }
}