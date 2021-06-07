package android.josephhowerton.travelingsalesman.data.foursquare

data class FoursquareCategoryRequest(
    val meta: FoursquareMeta,
    val response: FoursquareCategoryResponse
)
data class FoursquareCategoryResponse(
    val categories: List<FoursquareCategory>
)

data class FoursquareCategory(
    val categories: List<FoursquareCategory>,
    val icon: FoursquareIcon,
    val id: String,
    val name: String,
    val pluralName: String,
    val shortName: String
)