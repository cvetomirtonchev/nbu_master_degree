package tsvetomir.tonchev.findit.ui.base

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val loading = mutableStateOf(false)

    protected fun showLoading() {
        loading.value = true
    }

    protected fun hideLoading() {
        loading.value = false
    }
}