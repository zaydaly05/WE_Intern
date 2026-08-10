package com.example.formalpullrequest.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.formalpullrequest.analytics.AnalyticsManager
import com.example.formalpullrequest.domain.repository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CustomerRepository,
    private val analyticsManager: AnalyticsManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeUiState(
            isLoading = true
        )
    )

    val uiState: StateFlow<HomeUiState> =
        _uiState.asStateFlow()

    init {
        analyticsManager.logScreenView("Home")

        observeCustomer()
    }

    private fun observeCustomer() {

        viewModelScope.launch {

            repository.observeCustomer()
                .collect { customer ->

                    _uiState.update {
                        it.copy(
                            customer = customer,
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun loadCustomer(
        customerId: String
    ) {

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            try {

                repository.refreshCustomer(
                    customerId = customerId
                )

            } catch (exception: Exception) {

                _uiState.update {
                    it.copy(
                        errorMessage = "Unable to load your account."
                    )
                }

            } finally {

                _uiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }

    fun refresh(
        customerId: String
    ) {

        viewModelScope.launch {

            analyticsManager.logRefresh()

            _uiState.update {
                it.copy(
                    isRefreshing = true,
                    errorMessage = null
                )
            }

            try {

                repository.refreshCustomer(
                    customerId = customerId
                )

            } catch (exception: Exception) {

                _uiState.update {
                    it.copy(
                        errorMessage = "Unable to refresh your account."
                    )
                }

            } finally {

                _uiState.update {
                    it.copy(
                        isRefreshing = false
                    )
                }
            }
        }
    }
}