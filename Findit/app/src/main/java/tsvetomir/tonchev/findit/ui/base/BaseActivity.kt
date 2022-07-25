package tsvetomir.tonchev.findit.ui.base

import androidx.activity.ComponentActivity

abstract class BaseActivity : ComponentActivity() {

    protected abstract val viewModel: BaseViewModel
}