package android.josephhowerton.travelingsalesman.ui.auth.register

import android.josephhowerton.travelingsalesman.ui.auth.login.LoggedInUserView

data class RegisterResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null,
    val message: String? = null
)