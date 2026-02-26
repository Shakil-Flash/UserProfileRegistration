package com.flash.userprofileregistration.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val timestamp: Long = System.currentTimeMillis()
) : Parcelable
