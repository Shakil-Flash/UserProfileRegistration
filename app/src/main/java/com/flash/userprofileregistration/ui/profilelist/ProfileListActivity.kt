package com.flash.userprofileregistration.ui.profilelist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.flash.userprofileregistration.R
import com.flash.userprofileregistration.databinding.ActivityProfileListBinding
import com.flash.userprofileregistration.ui.addprofile.AddProfileActivity
import com.flash.userprofileregistration.ui.singleprofile.SingleProfileActivity
import com.flash.userprofileregistration.viewmodel.ProfileViewModel

class ProfileListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileListBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var adapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProfileListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        // Initialize Adapter
        adapter = ProfileAdapter(
            onEditClick = { profile ->
                // Open AddProfileActivity with profile for editing
                val intent = Intent(this, AddProfileActivity::class.java)
                intent.putExtra("profile", profile)
                startActivity(intent)
            },
            onDeleteClick = { profile ->
                // Delete profile
                viewModel.delete(profile)
                Toast.makeText(this, "Profile deleted", Toast.LENGTH_SHORT).show()
            },
            onItemClick = { profile ->
                // Open SingleProfileActivity to view details
                val intent = Intent(this, SingleProfileActivity::class.java)
                intent.putExtra("id", profile.id)
                startActivity(intent)
            }
        )

        // RecyclerView setup
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Observe profiles list
        viewModel.allProfiles.observe(this) { list ->
            adapter.submitList(list)
        }

        // Observe total profile count
        viewModel.totalProfiles.observe(this) { count ->
            binding.tvTotal.text = "Total Profiles: $count"
        }

        // FAB Add button
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddProfileActivity::class.java))
        }
    }
}