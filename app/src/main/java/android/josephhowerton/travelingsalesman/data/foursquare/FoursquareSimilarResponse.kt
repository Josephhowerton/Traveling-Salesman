package android.josephhowerton.travelingsalesman.data.foursquare

data class FoursquareSimilarRequest(
    val meta: FoursquareMeta,
    val response: FoursquareSimilarResponse
)

data class FoursquareSimilarResponse(
    val similarVenues: FoursquareSimilarVenues
)

data class FoursquareSimilarVenues(
    val count: Int,
    val items: List<FoursquareSimilarVenuesItem>
)

data class FoursquareSimilarVenuesItem(
    val categories: List<FoursquareCategory>,
    val id: String,
    val location: FoursquareLocation,
    val name: String,
    val venuePage: FoursquareSimilarVenuesItemVenuePage
)

data class FoursquareSimilarVenuesItemVenuePage(
    val id: String
)