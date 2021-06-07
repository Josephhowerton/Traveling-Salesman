package android.josephhowerton.travelingsalesman.ui.auth.interest

import android.josephhowerton.travelingsalesman.R
import android.josephhowerton.travelingsalesman.data.Repository
import android.josephhowerton.travelingsalesman.data.foursquare.FoursquareCategory
import android.josephhowerton.travelingsalesman.network.foursquare.FoursquareResult
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class PickInterestViewModel(private val repository: Repository) : ViewModel() {

    private val _continueStep = MutableLiveData<Boolean>()
    val continueStep: LiveData<Boolean>
        get() = _continueStep

    private val _animate = MutableLiveData<Boolean>()
    val animate: LiveData<Boolean>
        get() = _animate

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    private val _placesCategories = MutableLiveData<FoursquareResult<List<FoursquareCategory>>>()
    val  placesCategories: LiveData<FoursquareResult<List<FoursquareCategory>>>
        get() = _placesCategories

    val foursquareTest = liveData(Dispatchers.IO) {
        emit(repository.getFoursquareCategories())
    }


    var destination: Int? = null

    init {
        _animate.value = false
        _continueStep.value = false
        _isLoading.value = false
    }

    fun onContinueClick(view: View){
        _continueStep.value = true
    }

    fun continueStep(shouldGo: Boolean){
        if(shouldGo){
            destination = R.id.action_pickInterestFragment_to_navigation_push
            _animate.value = true
        }
    }

    fun fetchPlacesCategories(){
    }
}