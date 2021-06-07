package android.josephhowerton.travelingsalesman.data.foursquare

data class FoursquareDetailRequest(
    val meta: FoursquareMeta,
    val response: FoursquareDetailResponse
)

data class FoursquareDetailResponse(
    val venue: FoursquareVenue
)
