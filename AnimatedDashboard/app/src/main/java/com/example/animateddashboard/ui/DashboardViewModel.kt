package com.example.animateddashboard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animateddashboard.data.DashboardRepository
import com.example.animateddashboard.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val repository =
        DashboardRepository(RetrofitInstance.api)

    private val _uiState =
        MutableStateFlow<UiState>(UiState.Loading)

    val uiState: StateFlow<UiState> =
        _uiState.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {

        viewModelScope.launch {

            _uiState.value = UiState.Loading

            try {

                val result =
                    repository.fetchDashboardData()

                _uiState.value =
                    UiState.Success(result)

            } catch (e: Exception) {

                _uiState.value =
                    UiState.Error(
                        e.message ?: "Network request failed"
                    )
            }
        }
    }
}