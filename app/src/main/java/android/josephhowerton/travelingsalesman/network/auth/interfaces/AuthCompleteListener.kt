package android.josephhowerton.travelingsalesman.network.auth.interfaces

import android.josephhowerton.travelingsalesman.network.auth.AuthResult
import android.josephhowerton.travelingsalesman.data.model.LoggedInUser

interface AuthCompleteListener {
    fun onSuccess(result: AuthResult<LoggedInUser>)
    fun onFailed(result: AuthResult<LoggedInUser>)
}