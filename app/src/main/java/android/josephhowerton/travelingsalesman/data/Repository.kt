package android.josephhowerton.travelingsalesman.data

import android.app.Application
import android.josephhowerton.travelingsalesman.data.auth.AuthSource
import android.josephhowerton.travelingsalesman.data.auth.`interface`.AuthCompleteListener
import android.josephhowerton.travelingsalesman.data.auth.`interface`.ResetCompleteListener

// @see https://developer.android.com/training/articles/keystore
// TODO("credentials will be cached in local storage, it is recommended it be encrypted")
class Repository private constructor(application: Application){
    companion object {
        private var instance: Repository? = null
        fun getInstance(application: Application) : Repository {
            if(instance == null){
                instance = Repository(application)
            }
            return instance!!
        }
    }


    private val authSource: AuthSource
        = AuthSource()

    fun registerWithEmail(name: String, email: String, password: String, listener: AuthCompleteListener){
        authSource.registerWithEmail(email, password, listener)
    }

    fun loginWithEmail(email: String, password: String, listener: AuthCompleteListener){
        authSource.loginWithEmail(email, password, listener)
    }

    fun loginWithGoogle(idToken: String, listener: AuthCompleteListener){
        authSource.loginWithGoogle(idToken, listener)
    }

    fun sendPasswordResetEmail(email: String, listener: ResetCompleteListener){
        authSource.sendPasswordResetEmail(email, listener)
    }

    fun logout() = authSource.logout()

}