package android.josephhowerton.travelingsalesman.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SplashViewModel: ViewModel() {

    private val _isUserSignedIn: MutableLiveData<Boolean> = MutableLiveData()
    val isUserSignedIn: LiveData<Boolean> get() = _isUserSignedIn

    private val _sendToAuth: MutableLiveData<Boolean> = MutableLiveData()
    val sendToAuth: LiveData<Boolean> get() = _sendToAuth

    private val _sendToMain: MutableLiveData<Boolean> = MutableLiveData()
    val sendToMain: LiveData<Boolean> get() = _sendToMain

    init{
        _isUserSignedIn.value = FirebaseAuth.getInstance().currentUser != null
    }

    fun navigate(whereTo: Boolean){
        if(whereTo){
            _sendToMain.value = true
        }
        else{
            _sendToAuth.value = true
        }
    }
}