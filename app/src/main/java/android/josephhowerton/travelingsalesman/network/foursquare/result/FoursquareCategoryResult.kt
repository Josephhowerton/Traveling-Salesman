package android.josephhowerton.travelingsalesman.network.foursquare.result

import android.josephhowerton.travelingsalesman.data.foursquare.FoursquareCategory
import android.josephhowerton.travelingsalesman.data.foursquare.FoursquareMeta

data class FoursquareCategoryResult(
        val results: List<FoursquareCategory>,
        val meta: FoursquareMeta
)