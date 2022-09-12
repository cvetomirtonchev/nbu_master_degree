package tsvetomir.tonchev.findit.data.network.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import tsvetomir.tonchev.findit.data.network.model.request.SearchEventRequest

interface TrackingService {
    @POST("api/track/event")
    suspend fun trackSearchEvent(@Body searchEventRequest: SearchEventRequest): Response<Void>
}