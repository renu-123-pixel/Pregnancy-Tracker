package com.heartratemonitor.pregnancyvitalstrackerwithreminders.repository

import com.heartratemonitor.pregnancyvitalstrackerwithreminders.data.local.VitalsDao
import com.heartratemonitor.pregnancyvitalstrackerwithreminders.data.local.VitalsEntity

class VitalsRepository(private val dao: VitalsDao) {

    val allVitals = dao.getAllVitals()

    suspend fun insert(vitals: VitalsEntity) {
        dao.insertVitals(vitals)
    }

//    suspend fun insert(vitals: VitalsEntity): Long {
//        return dao.insertVitals(vitals)
//    }
}


