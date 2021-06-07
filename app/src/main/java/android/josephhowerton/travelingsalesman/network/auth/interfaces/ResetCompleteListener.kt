package android.josephhowerton.travelingsalesman.network.auth.interfaces

import android.josephhowerton.travelingsalesman.network.auth.AuthResult
import android.josephhowerton.travelingsalesman.ui.auth.reset.ResetEmail

interface ResetCompleteListener {
    fun onSuccess(result: AuthResult<ResetEmail>)
    fun onFailed(result: AuthResult<ResetEmail>)
}