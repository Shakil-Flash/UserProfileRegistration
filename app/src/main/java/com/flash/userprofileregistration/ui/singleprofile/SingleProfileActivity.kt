package com.flash.userprofileregistration.ui.singleprofile

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.flash.userprofileregistration.R
import com.flash.userprofileregistration.databinding.ActivitySingleProfileBinding
import com.flash.userprofileregistration.viewmodel.ProfileViewModel

class SingleProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        val profileId = intent.getIntExtra("id", -1)
        if (profileId == -1) {
            Toast.makeText(this, "Invalid profile", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        viewModel.getProfileById(profileId).observe(this) { profile ->
            profile?.let {
                binding.tvName.text = it.name
                binding.tvEmail.text = it.email
                binding.tvPhone.text = it.phone
                binding.tvAddress.text = it.address
            }
        }
    }
}