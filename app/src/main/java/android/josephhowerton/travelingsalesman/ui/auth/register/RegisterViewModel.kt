package android.josephhowerton.travelingsalesman.ui.auth.register

import android.content.Intent
import android.josephhowerton.travelingsalesman.R
import android.josephhowerton.travelingsalesman.data.Repository
import android.josephhowerton.travelingsalesman.network.auth.AuthResult
import android.josephhowerton.travelingsalesman.network.auth.interfaces.AuthCompleteListener
import android.josephhowerton.travelingsalesman.data.model.LoggedInUser
import android.josephhowerton.travelingsalesman.ui.auth.login.LoggedInUserView
import android.util.Patterns
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException

class RegisterViewModel(private val repository: Repository) : ViewModel(){

    private val _registerForm = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _registerForm

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    private val _signUpWithGoogle = MutableLiveData<Boolean>()
    val signUpWithGoogle: LiveData<Boolean>
        get() = _signUpWithGoogle

    private val _animate = MutableLiveData<Boolean>()
    val animate: LiveData<Boolean>
        get() = _animate

    private val _isLoading = MutableLiveData<Int>()
    val isLoading: LiveData<Int> get() = _isLoading

    var destination:Int? = null

    var name:String = ""
    var email:String = ""
    var password:String = ""

    init {
        _isLoading.value = View.GONE
        _signUpWithGoogle.value = false
        _registerForm.value = RegisterFormState(isDataValid = false)
    }

    fun onNavigateBack(view: View){
        destination = R.id.action_navigation_register_to_navigation_auth
        _animate.value = true
    }

    fun onSignUpWithGoogle(view: View){
        _signUpWithGoogle.value = true
    }

    fun onSignUpWithEmail(view: View){
        _isLoading.value = View.VISIBLE
        signUpWithEmail()
    }

    // Google Sign In was successful, authenticate with Firebase
    fun signUpWithGoogle(data: Intent?){
        try {
            _isLoading.value = View.VISIBLE
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)!!
            repository.loginWithGoogle(account.idToken!!,authCompleteListener)

        } catch (e: ApiException) {
            _registerResult.value = RegisterResult(error = R.string.login_failed)
            _isLoading.value = View.GONE
        }
    }

    fun signUpWithEmail() {
        repository.registerWithEmail(name, email, password, authCompleteListener)
    }

    fun registerDataChanged() {
        if (!isEmailValid()) {
            _registerForm.value = RegisterFormState(emailError = R.string.invalid_username)
        } else if (!isPasswordValid()) {
            _registerForm.value = RegisterFormState(passwordError = R.string.invalid_password)
        } else {
            _registerForm.value = RegisterFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isEmailValid(): Boolean {
        return if (email.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(): Boolean {
        return password.length > 5
    }

    private val authCompleteListener = object: AuthCompleteListener{
        override fun onSuccess(result: AuthResult<LoggedInUser>) {
            if (result is AuthResult.Success) {
                _registerResult.value = RegisterResult(success = LoggedInUserView(displayName = result.data.displayName))
            }
            _animate.value = true
            _isLoading.value = View.GONE
            destination = R.id.action_navigation_register_to_navigation_welcome
        }

        override fun onFailed(result: AuthResult<LoggedInUser>) {
            if (result is AuthResult.Error) {
                _registerResult.value = RegisterResult(message = result.exception.message)
            }
            else {
                _registerResult.value = RegisterResult(error = R.string.login_failed)
            }
            _isLoading.value = View.GONE
        }
    }
}