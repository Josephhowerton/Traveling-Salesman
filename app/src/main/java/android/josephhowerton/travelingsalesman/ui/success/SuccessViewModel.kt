package android.josephhowerton.travelingsalesman.ui.success

import android.josephhowerton.travelingsalesman.data.Repository
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SuccessViewModel(private val repository: Repository): ViewModel() {

    private val _continueClick = MutableLiveData<Boolean>()
    val continueClick: LiveData<Boolean> get() = _continueClick

    private val _navigate = MutableLiveData<Int?>()
    val navigate: LiveData<Int?> get() = _navigate

    private val _optionalText = MutableLiveData<String>()
    val optionalText: LiveData<String>
        get() = _optionalText

    init {
        _continueClick.value = false
    }

    fun onContinueClick(view: View){
        println("onContinueClick")
        _continueClick.value = true
    }

    fun navigate(shouldTravel: Boolean, whereTo: Int?){
        println("navigate " + whereTo)
        if(shouldTravel && whereTo != null) _navigate.value = whereTo
    }
}