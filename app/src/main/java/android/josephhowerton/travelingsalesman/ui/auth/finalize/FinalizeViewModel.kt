package android.josephhowerton.travelingsalesman.ui.auth.finalize

import android.josephhowerton.travelingsalesman.data.Repository
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FinalizeViewModel(private val repository: Repository) : ViewModel() {

    private val _finishInitialization = MutableLiveData<Boolean>();
    val finishInitialization : LiveData<Boolean>
        get() = _finishInitialization

    private val _animate = MutableLiveData<Boolean>();
    val animate: LiveData<Boolean>
        get() = _animate

    private val _isLoading = MutableLiveData<Int>()
    val isLoading: LiveData<Int> get() = _isLoading

    init {
        _animate.value = false
        _finishInitialization.value = false
    }

    fun onInitializationFinished(isFinished: Boolean){
        if (isFinished){
            _isLoading.value = View.GONE
            _animate.value = true
        }
    }
}