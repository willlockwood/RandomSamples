package com.willlockwood.lockwoodtakehome.data.repository

import androidx.lifecycle.LiveData
import com.willlockwood.lockwoodtakehome.data.dao.LockwoodDao
import com.willlockwood.lockwoodtakehome.data.model.Lockwood

class LockwoodRepository(
    private val lockwoodDao: LockwoodDao
) {

    fun getAllLockwoods(): LiveData<List<Lockwood>> = lockwoodDao.getAllLockwoods()
    suspend fun deleteLockwoods(lockwood: Lockwood) = lockwoodDao.deleteAll()
    suspend fun insertLockwoods(lockwood: Lockwood) = lockwoodDao.insert(lockwood)
    suspend fun insertLockwoods(lockwoods: List<Lockwood>) = lockwoodDao.insert(lockwoods)
//    fun getEdictsByDeadlineType(deadlineType: String): List<Edict> = edictDao.getEdictsWithDeadlineType(deadlineType)
//
//    fun deleteEdict(edict: Edict) = edictDao.deleteEdict(edict)
//    fun updateEdict(edict: Edict) = edictDao.updateEdict(edict)
//    fun insertEdict(edict: Edict) = edictDao.insertEdict(edict)
//    fun insertEdictGetId(edict: Edict): Long = edictDao.insertEdictGetId(edict)
//
//    fun getLiveEdictById(id: Int): LiveData<Edict> = edictDao.getLiveEdictById(id)
//    suspend fun getEdictById(id: Int): Edict = edictDao.getEdictById(id)
//
//    fun getActiveEdictSessions(): LiveData<List<EdictSession>> = edictSessionDao.getActiveEdictSessions()
//
//    suspend fun insertEdictSessions(edictSession: EdictSession) = edictSessionDao.insertEdictSessions(edictSession)
//
//    fun getEdictSessionsById(id: Int): LiveData<List<EdictSession>> = edictSessionDao.getEdictSessionsByEdictId(id)
//
////    Unused so far
////    fun updateEdict(edict: Edict) = edictDao.updateEdict(edict)
////    fun updateEdictSession(edictSession: EdictSession) = edictSessionDao.updateEdictSession(edictSession)
//
////    suspend fun insertEdictSessions(edictSessions: List<EdictSession>) = edictSessionDao.insertEdictSessions(edictSessions)
}