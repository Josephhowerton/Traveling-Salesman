package android.josephhowerton.travelingsalesman.ui.auth.register

data class RegisterFormState(
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)