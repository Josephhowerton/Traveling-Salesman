package android.josephhowerton.travelingsalesman.data.auth.`interface`

import android.josephhowerton.travelingsalesman.data.auth.AuthResult
import android.josephhowerton.travelingsalesman.ui.auth.reset.ResetEmail

interface ResetCompleteListener {
    fun onSuccess(result: AuthResult<ResetEmail>)
    fun onFailed(result: AuthResult<ResetEmail>)
}