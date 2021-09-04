package com.pascaciorc.checkpoint.viewmodel

import androidx.lifecycle.ViewModel
import com.pascaciorc.checkpoint.data.CheckpointRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: CheckpointRepository
): ViewModel() {

}