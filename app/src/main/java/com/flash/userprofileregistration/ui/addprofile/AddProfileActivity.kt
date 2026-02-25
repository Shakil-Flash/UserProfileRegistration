package com.flash.userprofileregistration.ui.addprofile

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.flash.userprofileregistration.R
import com.flash.userprofileregistration.data.local.Profile
import com.flash.userprofileregistration.databinding.ActivityAddProfileBinding
import com.flash.userprofileregistration.viewmodel.ProfileViewModel

class AddProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private var profileToEdit: Profile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        // Check if editing
        profileToEdit = intent.getSerializableExtra("profile") as? Profile
        profileToEdit?.let { profile ->
            binding.etName.setText(profile.name)
            binding.etEmail.setText(profile.email)
            binding.etPhone.setText(profile.phone)
            binding.etAddress.setText(profile.address)
        }

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()
            val address = binding.etAddress.text.toString().trim()

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Name and Email required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val profile = Profile(
                id = profileToEdit?.id ?: 0,
                name = name,
                email = email,
                phone = phone,
                address = address
            )

            if (profileToEdit == null) {
                viewModel.insert(profile)
                Toast.makeText(this, "Profile added", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.update(profile)
                Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show()
            }

            finish() // Go back to list
        }
    }
}