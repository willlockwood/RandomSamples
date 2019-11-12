package com.willlockwood.lockwoodtakehome.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.willlockwood.lockwoodtakehome.data.model.Lockwood

@Dao
interface LockwoodDao {

    @Query("SELECT * from lockwoods")
    fun getAllLockwoods(): LiveData<List<Lockwood>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: Lockwood)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lockwoods: List<Lockwood>)

    @Query("DELETE FROM lockwoods")
    suspend fun deleteAll()
}