package tsvetomir.tonchev.findit.utils.datastore

import tsvetomir.tonchev.findit.data.network.model.response.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionStorage @Inject constructor() {
    private var _user: User? = null
    var user: User?
        get() = _user
        set(value) {
            _user = value
        }
}