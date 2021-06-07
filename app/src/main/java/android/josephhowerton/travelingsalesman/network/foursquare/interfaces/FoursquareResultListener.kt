package android.josephhowerton.travelingsalesman.network.foursquare.interfaces

import android.josephhowerton.travelingsalesman.data.model.LoggedInUser
import android.josephhowerton.travelingsalesman.network.foursquare.FoursquareResult

interface FoursquareResultListener {
    fun onSuccess(result: FoursquareResult<LoggedInUser>)
    fun onFailed(result: FoursquareResult<LoggedInUser>)
}