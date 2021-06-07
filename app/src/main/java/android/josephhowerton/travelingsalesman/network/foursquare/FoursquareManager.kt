package android.josephhowerton.travelingsalesman.network.foursquare

import android.josephhowerton.travelingsalesman.BuildConfig

object FoursquareManager {
    private const val CLIENT_ID_FIELD = "client_id"
    private const val CLIENT_SECRET_FIELD = "client_secret"
    private const val LOCATION_PARAM = "ll"
    private const val RADIUS_PARAM = "radius"
    private const val QUERY_PARAM = "query"
    private const val CATEGORY_PARAM = "categoryId"
    private const val VERSIONING_PARAM = "v"
    private const val RADIUS_DEFAULT_VALUE = "100000"

    fun buildQueryMap(): HashMap<String, String> {
        val searchMap: HashMap<String, String> = HashMap()
        searchMap[CLIENT_ID_FIELD] = BuildConfig.foursquare_client_id
        searchMap[CLIENT_SECRET_FIELD] = BuildConfig.foursquare_client_secret
        searchMap[VERSIONING_PARAM] = BuildConfig.foursquare_version
        return searchMap
    }

    fun buildCoordinatesQueryMap(lat: Double, lon: Double): HashMap<String, String> {
        val searchMap: HashMap<String, String> = HashMap()
        searchMap[CLIENT_ID_FIELD] = BuildConfig.foursquare_client_id
        searchMap[CLIENT_SECRET_FIELD] = BuildConfig.foursquare_client_secret
        searchMap[VERSIONING_PARAM] = BuildConfig.foursquare_version
        searchMap[RADIUS_PARAM] = RADIUS_DEFAULT_VALUE
        searchMap[LOCATION_PARAM] = "$lat,$lon"
        return searchMap
    }

    fun buildSearchQueryMap(lat: Double, lon: Double, query: String): HashMap<String, String> {
        val searchMap: HashMap<String, String> = HashMap()
        searchMap[CLIENT_ID_FIELD] = BuildConfig.foursquare_client_id
        searchMap[CLIENT_SECRET_FIELD] = BuildConfig.foursquare_client_secret
        searchMap[VERSIONING_PARAM] = BuildConfig.foursquare_version
        searchMap[RADIUS_PARAM] = RADIUS_DEFAULT_VALUE
        searchMap[LOCATION_PARAM] = "$lat,$lon"
        searchMap[QUERY_PARAM] = query
        return searchMap
    }

    fun buildSearchCategoryMap(lat: Double, lon: Double, categoryId: String): HashMap<String, String> {
        val searchMap: HashMap<String, String> = HashMap()
        searchMap[CLIENT_ID_FIELD] = BuildConfig.foursquare_client_id
        searchMap[CLIENT_SECRET_FIELD] = BuildConfig.foursquare_client_secret
        searchMap[VERSIONING_PARAM] = BuildConfig.foursquare_version
        searchMap[RADIUS_PARAM] = RADIUS_DEFAULT_VALUE
        searchMap[LOCATION_PARAM] = "$lat,$lon"
        searchMap[CATEGORY_PARAM] = categoryId
        return searchMap
    }
}
