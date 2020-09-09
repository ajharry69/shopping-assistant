package com.xently.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Token(
    @PrimaryKey(autoGenerate = false) val id: Long = 1,
    val access: String,
    val refresh: String,
) {
    override fun toString(): String = "Bearer $access"
}