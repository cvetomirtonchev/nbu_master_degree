package tsvetomir.tonchev.findit.ui.components.model

sealed class LoadingIndicatorState {
    object ShowLoading : LoadingIndicatorState()
    object HideLoading : LoadingIndicatorState()
}