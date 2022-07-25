package tsvetomir.tonchev.findit.utils

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchersProvider {
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}