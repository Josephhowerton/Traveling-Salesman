package android.josephhowerton.travelingsalesman.network.foursquare.interfaces

import android.josephhowerton.travelingsalesman.data.foursquare.*

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface FoursquareService {

    @GET("/v2/venues/categories")
    suspend fun getFoursquareCategories(@QueryMap argumentMap: HashMap<String, String>): FoursquareCategoryRequest

    @GET("/v2/venues/search")
    suspend fun searchFoursquareVenues(@QueryMap argumentMap: HashMap<String, String>): FoursquareSearchRequest

    @GET("/v2/venues/{venue_id}/similar")
    suspend fun getSimilarFoursquareVenues(@Path("venue_id") venueId: String, @QueryMap argumentMap: HashMap<String, String>): FoursquareSimilarRequest

    @GET("/v2/search/recommendations")
    suspend fun getRecommendedFoursquareVenues(@QueryMap argumentMap: HashMap<String, String>): FoursquareRecommendationRequest

    @GET("/v2/venues/{venue_id}")
    suspend fun getFoursquareVenueDetails(@Path("venue_id") venueId: String?, @QueryMap argumentMap: HashMap<String, String>): FoursquareDetailRequest
}