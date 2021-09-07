package com.pascaciorc.checkpoint.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pascaciorc.checkpoint.data.Checkpoint
import com.pascaciorc.checkpoint.data.CheckpointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    repository: CheckpointRepository
) : ViewModel() {
    val checkpoints: LiveData<List<Checkpoint>> = repository.getCheckpoints().asLiveData()
}