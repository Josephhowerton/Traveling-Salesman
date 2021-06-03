package android.josephhowerton.travelingsalesman.ui.auth.reset

import android.josephhowerton.travelingsalesman.R
import android.josephhowerton.travelingsalesman.data.Repository
import android.josephhowerton.travelingsalesman.data.auth.AuthResult
import android.josephhowerton.travelingsalesman.data.auth.`interface`.ResetCompleteListener
import android.josephhowerton.travelingsalesman.ui.auth.login.LoginFormState
import android.josephhowerton.travelingsalesman.ui.auth.register.RegisterFormState
import android.util.Patterns
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResetPasswordViewModel(private val repository: Repository) : ViewModel() {

    private val _resetFormState = MutableLiveData<LoginFormState>()
    val resetFormState: LiveData<LoginFormState> = _resetFormState

    private val _resetEmailResult = MutableLiveData<ResetEmail>()
    val resultEmail: LiveData<ResetEmail> = _resetEmailResult

    private val _animate = MutableLiveData<Boolean>()
    val animate: LiveData<Boolean>
        get() = _animate

    private val _isLoading = MutableLiveData<Int>()
    val isLoading: LiveData<Int> get() = _isLoading

    private val _btnVisible = MutableLiveData<Int>()
    val btnVisible: LiveData<Int> get() = _btnVisible

    private val _successLayoutVisible = MutableLiveData<Int>()
    val successLayoutVisible: LiveData<Int> get() = _successLayoutVisible

    var destination:Int? = null

    var email:String = ""

    init {
        _isLoading.value = View.GONE
        _btnVisible.value = View.VISIBLE
        _successLayoutVisible.value = View.GONE
        _resetFormState.value = LoginFormState(isDataValid = false)
    }

    fun onNavigateBack(view: View){
        destination = R.id.action_navigation_reset_password_to_navigation_login
        _animate.value = true
    }

    fun onResetClick(view: View){
        _isLoading.value = View.VISIBLE
        repository.sendPasswordResetEmail(email, object : ResetCompleteListener{
            override fun onSuccess(result: AuthResult<ResetEmail>) {
                if (result is AuthResult.Success) {
                    _resetEmailResult.value = ResetEmail(success = result.data.success)
                    _successLayoutVisible.value = View.VISIBLE
                    _btnVisible.value = View.GONE
                }
                _isLoading.value = View.GONE
            }

            override fun onFailed(result: AuthResult<ResetEmail>) {
                if (result is AuthResult.Error) {
                    _resetEmailResult.value = ResetEmail(message = result.exception.message)
                } else {
                    _resetEmailResult.value = ResetEmail(error = R.string.reset_password_failed)
                }
                _isLoading.value = View.GONE
            }
        })
    }

    fun loginDataChanged() {
        if (!isEmailValid()) {
            _resetFormState.value = LoginFormState(emailError = R.string.invalid_username)
        }
        else {
            _resetFormState.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isEmailValid(): Boolean {
        return if (email.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }
}