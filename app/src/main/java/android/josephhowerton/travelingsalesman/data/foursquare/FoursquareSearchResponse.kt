package android.josephhowerton.travelingsalesman.data.foursquare

data class FoursquareSearchRequest(
    val meta: FoursquareMeta,
    val response: FoursquareSearchResponse
)

data class FoursquareSearchResponse(
    val venues: List<FoursquareVenue>
)