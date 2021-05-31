package android.josephhowerton.travelingsalesman.ui.auth.auth

import android.josephhowerton.travelingsalesman.R
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.properties.Delegates

class AuthViewModel : ViewModel() {


    private val _signInWithEmail = MutableLiveData<Boolean>()
    val signInWithEmail: LiveData<Boolean>
        get() = _signInWithEmail

    private val _signInWithGoogle = MutableLiveData<Boolean>()
    val signInWithGoogle: LiveData<Boolean>
        get() = _signInWithGoogle

    private val _signUp = MutableLiveData<Boolean>()
    val signUp: LiveData<Boolean>
        get() = _signUp

    private val _animate = MutableLiveData<Boolean>()
    val animate: LiveData<Boolean>
        get() = _animate

    private val _navigate = MutableLiveData<Int?>()
    val navigate: LiveData<Int?>
        get() = _navigate

    var destination by Delegates.notNull<Int>()

    init{
        _signUp.value = false
        _navigate.value = null
        _signInWithEmail.value = false
        _signInWithGoogle.value = false
    }

    fun onSignInWithEmailClicked(view: View){
        _signInWithEmail.value = true
    }

    fun signInWithEmail(shouldGo: Boolean){
        if(shouldGo){
            _animate.value = shouldGo
        }
    }

    fun onSignInWithGoogleClicked(view: View){
        _signInWithGoogle.value = true
    }


    fun signInWithGoogle(shouldGo: Boolean){
        if(shouldGo){
            _animate.value = shouldGo
        }
    }

    fun onSignUpClicked(view: View){
        _signUp.value = true
    }

    fun signUp(shouldGo: Boolean){
        if(shouldGo){
            _animate.value = shouldGo
        }
    }

    fun animate(shouldAnimate: Boolean, whereTo: Int){
        if(shouldAnimate){
            destination = whereTo
        }
    }

    fun navigate(){
        _navigate.value = destination
    }
}