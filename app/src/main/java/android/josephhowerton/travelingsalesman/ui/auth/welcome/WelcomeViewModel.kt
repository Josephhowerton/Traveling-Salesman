package android.josephhowerton.travelingsalesman.ui.auth.welcome

import android.josephhowerton.travelingsalesman.R
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {

    private val _continueStep = MutableLiveData<Boolean>();
    val continueStep: LiveData<Boolean>
        get() = _continueStep

    private val _animate = MutableLiveData<Boolean>();
    val animate: LiveData<Boolean>
        get() = _animate

    var destination: Int? = null

    init {
        _animate.value = false
        _continueStep.value = false
    }

    fun onContinueClick(view: View){
        _continueStep.value = true
    }

    fun continueStep(shouldGo: Boolean){
        if(shouldGo){
            destination = R.id.action_navigation_welcome_to_pickInterestFragment
            _animate.value = true
        }
    }


}