package android.josephhowerton.travelingsalesman.network.foursquare

import android.josephhowerton.travelingsalesman.data.foursquare.*
import android.josephhowerton.travelingsalesman.network.Retrofit.RetrofitBuilder
import android.josephhowerton.travelingsalesman.network.foursquare.interfaces.FoursquareService

class FoursquareSource {
    companion object {
        private const val BASE_URL = "https://api.foursquare.com"
        private const val education = "4d4b7105d754a06372d81259"
        private const val professional = "4d4b7105d754a06375d81259"
        private const val residence = "4e67e38e036454776db1fb3a"
        private const val services = "4d4b7105d754a06378d81259"
        private const val travel = "4d4b7105d754a06379d81259"

        val listOfVoids:List<String> = listOf(
            education, professional, residence, services, travel
        )

        private const val entertainment = "4d4b7104d754a06370d81259"
        private const val event = "4d4b7105d754a06373d81259"
        private const val food = "4d4b7105d754a06374d81259"
        private const val nightlife = "4d4b7105d754a06376d81259"
        private const val outdoors = "4d4b7105d754a06377d81259"

        val listOfAcceptable:List<String> = listOf(
                entertainment, event, food, nightlife, outdoors
        )
    }

    private val foursquareService: FoursquareService =
        RetrofitBuilder.getFoursquareService(BASE_URL, FoursquareService::class.java)

    suspend fun getFoursquareCategoriesAsync(): FoursquareCategoryRequest {
        return foursquareService.getFoursquareCategories(FoursquareManager.buildQueryMap())
    }

    suspend fun searchFoursquareVenuesAsync(): FoursquareSearchRequest {
        return foursquareService.searchFoursquareVenues(FoursquareManager.buildQueryMap())
    }

    suspend fun getSimilarFoursquareVenuesAsync(venueId: String): FoursquareSimilarRequest {
        return foursquareService.getSimilarFoursquareVenues(venueId, FoursquareManager.buildQueryMap())
    }

    suspend fun getRecommendedFoursquareVenuesAsync(): FoursquareRecommendationRequest {
        return foursquareService.getRecommendedFoursquareVenues(FoursquareManager.buildQueryMap())
    }

    suspend fun getFoursquareVenueDetailsAsync(venueId: String): FoursquareDetailRequest {
        return foursquareService.getFoursquareVenueDetails(venueId, FoursquareManager.buildQueryMap())
    }
}