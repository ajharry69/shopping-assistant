package com.xently.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val username: String,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    @PrimaryKey(autoGenerate = false) val id: Long = 1,
)