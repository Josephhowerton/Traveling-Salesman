package android.josephhowerton.travelingsalesman.data.auth.`interface`

import android.josephhowerton.travelingsalesman.data.auth.AuthResult
import android.josephhowerton.travelingsalesman.data.model.LoggedInUser

interface AuthCompleteListener {
    fun onSuccess(result: AuthResult<LoggedInUser>)
    fun onFailed(result: AuthResult<LoggedInUser>)
}