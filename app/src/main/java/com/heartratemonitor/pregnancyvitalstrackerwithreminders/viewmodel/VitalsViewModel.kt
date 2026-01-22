package com.heartratemonitor.pregnancyvitalstrackerwithreminders.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.heartratemonitor.pregnancyvitalstrackerwithreminders.repository.VitalsRepository
import com.heartratemonitor.pregnancyvitalstrackerwithreminders.data.local.VitalsDatabase
import com.heartratemonitor.pregnancyvitalstrackerwithreminders.data.local.VitalsEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VitalsViewModel(application: Application) :
    AndroidViewModel(application) {

    private val dao =
        VitalsDatabase.getDatabase(application).vitalsDao()

    private val repository = VitalsRepository(dao)

    val vitalsList = repository.allVitals
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun addVitals(vitals: VitalsEntity) {
        viewModelScope.launch {
            repository.insert(vitals)
        }
    }
}
