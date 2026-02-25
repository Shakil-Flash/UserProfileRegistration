package com.flash.userprofileregistration.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.flash.userprofileregistration.data.local.AppDatabase
import com.flash.userprofileregistration.data.local.Profile
import com.flash.userprofileregistration.repository.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProfileRepository

    val allProfiles: LiveData<List<Profile>>
    val totalProfiles: LiveData<Int>

    init {
        val dao = AppDatabase.getDatabase(application).profileDao()
        repository = ProfileRepository(dao)
        allProfiles = repository.allProfiles
        totalProfiles = repository.totalProfiles
    }

    fun insert(profile: Profile) = viewModelScope.launch {
        repository.insert(profile)
    }

    fun update(profile: Profile) = viewModelScope.launch {
        repository.update(profile)
    }

    fun delete(profile: Profile) = viewModelScope.launch {
        repository.delete(profile)
    }

    fun getProfileById(id: Int) = repository.getProfileById(id)
}