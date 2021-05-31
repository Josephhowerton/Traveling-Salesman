package android.josephhowerton.travelingsalesman.ui.auth.register

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.properties.Delegates

class RegisterViewModel : ViewModel() {

    private val _signUpWithEmail = MutableLiveData<Boolean>();
    val signUpWithEmail: LiveData<Boolean>
        get() = _signUpWithEmail

    private val _signUpWithGoogle = MutableLiveData<Boolean>();
    val signUpWithGoogle: LiveData<Boolean>
        get() = _signUpWithGoogle

    private val _navigateBack = MutableLiveData<Boolean>();
    val navigateBack: LiveData<Boolean>
        get() = _navigateBack

    private val _animate = MutableLiveData<Boolean>()
    val animate: LiveData<Boolean>
        get() = _animate

    private val _navigate = MutableLiveData<Int?>()
    val navigate: LiveData<Int?>
        get() = _navigate

    var destination by Delegates.notNull<Int>()

    init {
        _signUpWithEmail.value = false
        _signUpWithGoogle.value = false
        _navigateBack.value = false
    }

    fun onSignUpWithEmail(view: View){
        _signUpWithEmail.value = true
    }

    fun signUpWithEmail(){

    }

    fun onSignUpWithGoogle(view: View){
        _signUpWithGoogle.value = true
    }

    fun signUpWithGoogle(){

    }

    fun onNavigateBack(view: View){
        _navigateBack.value = true
    }

    fun navigateBack(){

    }
}