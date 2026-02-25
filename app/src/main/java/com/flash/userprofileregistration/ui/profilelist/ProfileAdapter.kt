package com.flash.userprofileregistration.ui.profilelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flash.userprofileregistration.data.local.Profile
import com.flash.userprofileregistration.databinding.ItemProfileBinding

class ProfileAdapter(
    private val onEditClick: (Profile) -> Unit,
    private val onDeleteClick: (Profile) -> Unit,
    private val onItemClick: (Profile) -> Unit
) : ListAdapter<Profile, ProfileAdapter.ProfileViewHolder>(DiffCallback()) {

    class ProfileViewHolder(val binding: ItemProfileBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemProfileBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profile = getItem(position)

        holder.binding.tvName.text = profile.name
        holder.binding.tvEmail.text = profile.email

        holder.binding.btnEdit.setOnClickListener {
            onEditClick(profile)
        }

        holder.binding.btnDelete.setOnClickListener {
            onDeleteClick(profile)
        }

        holder.binding.root.setOnClickListener {
            onItemClick(profile)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Profile>() {
        override fun areItemsTheSame(oldItem: Profile, newItem: Profile)
                = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Profile, newItem: Profile)
                = oldItem == newItem
    }
}