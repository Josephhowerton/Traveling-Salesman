package android.josephhowerton.travelingsalesman.ui.auth.location

import android.josephhowerton.travelingsalesman.R
import android.josephhowerton.travelingsalesman.data.Repository
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LocationViewModel(private val repository: Repository) : ViewModel() {

    private val _skipStep = MutableLiveData<Boolean>()
    val skipStep: LiveData<Boolean>
        get() = _skipStep

    private val _continueStep = MutableLiveData<Boolean>()
    val continueStep: LiveData<Boolean>
        get() = _continueStep

    var destination:Int? = null

    private val _animate = MutableLiveData<Boolean>()
    val animate: LiveData<Boolean>
        get() = _animate

    init {
        _animate.value = false
        _skipStep.value = false
        _continueStep.value = false
    }


    fun onSkipClick(view: View){
        _skipStep.value = true
    }

    fun onContinueClick(view: View){
        _continueStep.value = true
    }

    fun skipToNext(shouldGo: Boolean){
        if(shouldGo){
            destination = R.id.action_navigation_location_to_navigation_finalize
            _animate.value = true
        }
    }

    fun continueStep(shouldGo: Boolean){
        if(shouldGo){
            destination = R.id.action_navigation_location_to_navigation_finalize
            _animate.value = true
        }
    }
}