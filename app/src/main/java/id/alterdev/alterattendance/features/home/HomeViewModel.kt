package id.alterdev.alterattendance.features.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _checkInEnabled = MutableStateFlow(true)
    val checkInEnabled: StateFlow<Boolean> = _checkInEnabled

    private val _checkOutEnabled = MutableStateFlow(false)
    val checkOutEnabled: StateFlow<Boolean> = _checkOutEnabled

    fun setCheckInEnabled(enabled: Boolean) {
        _checkInEnabled.value = enabled
    }

    fun setCheckOutEnabled(enabled: Boolean) {
        _checkOutEnabled.value = enabled
    }
}