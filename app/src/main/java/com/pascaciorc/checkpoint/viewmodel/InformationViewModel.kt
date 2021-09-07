package com.pascaciorc.checkpoint.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascaciorc.checkpoint.data.Checkpoint
import com.pascaciorc.checkpoint.data.CheckpointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InformationViewModel @Inject constructor(
    private val repository: CheckpointRepository
): ViewModel() {

    fun setCheckpoint(checkpoint: Checkpoint) {
        viewModelScope.launch {
            repository.insertCheckpoint(checkpoint)
        }
    }
}