package com.pascaciorc.checkpoint.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascaciorc.checkpoint.data.Checkpoint
import com.pascaciorc.checkpoint.data.CheckpointRepository
import com.pascaciorc.checkpoint.data.CheckpointState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InformationViewModel @Inject constructor(
    private val repository: CheckpointRepository
): ViewModel() {
    private val _state = MutableLiveData<CheckpointState>()
    val state: LiveData<CheckpointState> get() = _state

    fun setCheckpoint(checkpoint: Checkpoint) {
        viewModelScope.launch {
            val id = repository.insertCheckpoint(checkpoint)
            _state.value = CheckpointState.Success(id)
        }
    }
}