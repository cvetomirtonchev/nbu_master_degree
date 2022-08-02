package tsvetomir.tonchev.findit.ui.base

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val loading = mutableStateOf(false)

    protected fun showLoading(): Unit = loading.run { value = true }

    protected fun hideLoading(): Unit = loading.run { value = false }
}