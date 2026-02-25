package com.flash.userprofileregistration.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile ORDER BY timestamp DESC")
    fun getAllProfiles(): LiveData<List<Profile>>

    @Query("SELECT * FROM profile WHERE id = :id")
    fun getProfileById(id: Int): LiveData<Profile>

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profile: Profile)

    @Update
    suspend fun update(profile: Profile)

    @Delete
    suspend fun delete(profile: Profile)

    @Query("SELECT COUNT(*) FROM profile")
    fun getProfileCount(): LiveData<Int>

}