package android.josephhowerton.travelingsalesman.data

import android.app.Application
import android.josephhowerton.travelingsalesman.network.auth.AuthSource
import android.josephhowerton.travelingsalesman.network.auth.interfaces.AuthCompleteListener
import android.josephhowerton.travelingsalesman.network.auth.interfaces.ResetCompleteListener
import android.josephhowerton.travelingsalesman.network.foursquare.FoursquareSource

class Repository private constructor(application: Application){
    companion object {
        private var instance: Repository? = null
        fun getInstance(application: Application) : Repository {
            if(instance == null){
                instance = Repository(application)
            }
            return instance!!
        }
    }

    private val authSource: AuthSource = AuthSource()
    private val foursquareSource: FoursquareSource = FoursquareSource()

    fun registerWithEmail(name: String, email: String, password: String, listener: AuthCompleteListener){
        authSource.registerWithEmail(email, password, listener)
    }

    fun loginWithEmail(email: String, password: String, listener: AuthCompleteListener){
        authSource.loginWithEmail(email, password, listener)
    }

    fun loginWithGoogle(idToken: String, listener: AuthCompleteListener){
        authSource.loginWithGoogle(idToken, listener)
    }

    fun sendPasswordResetEmail(email: String, listener: ResetCompleteListener){
        authSource.sendPasswordResetEmail(email, listener)
    }

    fun logout() = authSource.logout()


    suspend fun getFoursquareCategories()
        = foursquareSource.getFoursquareCategoriesAsync()

    suspend fun searchFoursquareVenues()
        = foursquareSource.searchFoursquareVenuesAsync()

    suspend fun getSimilarFoursquareVenues(venueId: String)
        = foursquareSource.getSimilarFoursquareVenuesAsync(venueId)

    suspend fun getRecommendedFoursquareVenues()
        = foursquareSource.getRecommendedFoursquareVenuesAsync()

    suspend fun getFoursquareVenueDetails(venueId: String)
        = foursquareSource.getFoursquareVenueDetailsAsync(venueId)

}