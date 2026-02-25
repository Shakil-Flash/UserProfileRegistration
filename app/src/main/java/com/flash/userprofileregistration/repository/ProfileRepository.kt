package com.flash.userprofileregistration.repository

import androidx.lifecycle.LiveData
import com.flash.userprofileregistration.data.local.Profile
import com.flash.userprofileregistration.data.local.ProfileDao

class ProfileRepository(private val dao: ProfileDao) {

    val allProfiles = dao.getAllProfiles()
    val totalProfiles = dao.getProfileCount()

    suspend fun insert(profile: Profile) = dao.insert(profile)

    suspend fun update(profile: Profile) = dao.update(profile)

    suspend fun delete(profile: Profile) = dao.delete(profile)

    fun getProfileById(id: Int) = dao.getProfileById(id)
}