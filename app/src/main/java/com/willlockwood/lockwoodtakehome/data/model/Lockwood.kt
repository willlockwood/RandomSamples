package com.willlockwood.lockwoodtakehome.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="lockwoods")
data class Lockwood(
    var user: String,
    var likes: Int,
    var largeImageURL: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}