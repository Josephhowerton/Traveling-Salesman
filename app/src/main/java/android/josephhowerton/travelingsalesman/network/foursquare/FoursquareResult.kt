package android.josephhowerton.travelingsalesman.network.foursquare

sealed class FoursquareResult<out T : Any>  {
    data class Success<out T : Any>(val data: T) : FoursquareResult<T>()
    data class Error(val exception: Exception) : FoursquareResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}