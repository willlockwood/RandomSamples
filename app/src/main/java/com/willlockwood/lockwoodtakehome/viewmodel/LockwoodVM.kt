package com.willlockwood.lockwoodtakehome.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.willlockwood.lockwoodtakehome.data.db.LockwoodDatabase
import com.willlockwood.lockwoodtakehome.data.model.Lockwood
import com.willlockwood.lockwoodtakehome.data.repository.LockwoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LockwoodVM(application: Application) : AndroidViewModel(application) {

    private val lockwoodDao = LockwoodDatabase.getDatabase(application, viewModelScope).lockwoodDao()
    private val repository = LockwoodRepository(lockwoodDao)

    fun allLockwoods(): LiveData<List<Lockwood>> = repository.getAllLockwoods()

    fun deleteLockwood(lockwood: Lockwood) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteLockwoods(lockwood)
    }

    fun insertLockwoods(lockwoods: List<Lockwood>) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertLockwoods(lockwoods)
    }

    fun insertLockwood(lockwood: Lockwood) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertLockwoods(lockwood)
    }

}
