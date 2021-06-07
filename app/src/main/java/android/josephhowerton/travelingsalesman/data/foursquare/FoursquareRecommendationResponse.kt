package android.josephhowerton.travelingsalesman.data.foursquare

data class FoursquareRecommendationRequest(
    val meta: FoursquareMeta,
    val response: FoursquareRecommendationResponse
)

data class FoursquareRecommendationResponse(
    val groups: List<FoursquareRecommendationGroup>,
    val headerFullLocation: String,
    val headerLocation: String,
    val headerLocationGranularity: String,
    val query: String,
    val suggestedBounds: FoursquareRecommendationSuggestedBounds,
    val suggestedFilters: FoursquareRecommendationSuggestedFilters,
    val suggestedRadius: Int,
    val totalResults: Int
)

data class FoursquareRecommendationGroup(
    val items: List<FoursquareRecommendationItem>,
    val name: String,
    val type: String
)

data class FoursquareRecommendationSuggestedBounds(
    val ne: FoursquareRecommendationNe,
    val sw: FoursquareRecommendationSw
)

data class FoursquareRecommendationSuggestedFilters(
    val filters: List<FoursquareRecommendationFilter>,
    val header: String
)

data class FoursquareRecommendationItem(
    val flags: FoursquareRecommendationFlags,
    val reasons: FoursquareRecommendationReasons,
    val referralId: String,
    val venue: FoursquareVenue
)

data class FoursquareRecommendationFlags(
    val outsideRadius: Boolean
)

data class FoursquareRecommendationReasons(
    val count: Int,
    val items: List<FoursquareRecommendationReasonsItem>
)

data class FoursquareRecommendationReasonsItem(
    val reasonName: String,
    val summary: String,
    val type: String
)

data class FoursquareRecommendationLabeledLatLng(
    val label: String,
    val lat: Double,
    val lng: Double
)

data class FoursquareRecommendationNe(
    val lat: Double,
    val lng: Double
)

data class FoursquareRecommendationSw(
    val lat: Double,
    val lng: Double
)

data class FoursquareRecommendationFilter(
    val key: String,
    val name: String
)