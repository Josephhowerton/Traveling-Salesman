package android.josephhowerton.travelingsalesman.ui.auth.reset

import android.josephhowerton.travelingsalesman.ui.auth.login.LoggedInUserView

data class ResetEmail(
    val success: Boolean? = null,
    val error: Int? = null,
    val message: String? = null
)