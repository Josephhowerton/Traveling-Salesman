package android.josephhowerton.travelingsalesman.data.auth

import android.josephhowerton.travelingsalesman.data.auth.`interface`.AuthCompleteListener
import android.josephhowerton.travelingsalesman.data.auth.`interface`.ResetCompleteListener
import android.josephhowerton.travelingsalesman.data.model.LoggedInUser
import android.josephhowerton.travelingsalesman.ui.auth.reset.ResetEmail
import android.util.Log
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.IOException


class AuthSource{
    private val firebaseAuth: FirebaseAuth


    init{
       firebaseAuth = Firebase.auth
    }

    fun registerWithEmail(email: String, password: String, listener: AuthCompleteListener){
        try{
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                        listener.onSuccess(
                            AuthResult.Success(
                                LoggedInUser(
                                    user!!.uid,
                                    user.email!!
                                )
                            )
                        )
                    } else if (task.exception != null) {
                        var message = "Error logging in"
                        if(task.exception is FirebaseAuthWeakPasswordException){
                            message = "Password is too weak"
                        }
                        else if(task.exception is FirebaseAuthInvalidCredentialsException){
                            message = "Invalid credentials"
                        }
                        else if(task.exception is FirebaseAuthUserCollisionException){
                            message =  "Credentials already in use"
                        }
                        listener.onFailed(AuthResult.Error(IOException(message)))
                    }
                }
        }
        catch (e: Exception){
            listener.onFailed(AuthResult.Error(IOException(e)))
        }
    }

    fun loginWithEmail(email: String, password: String, listener: AuthCompleteListener){
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                        Log.println(Log.ASSERT, "loginWithEmail", "isSuccessful")
                        listener.onSuccess(
                            AuthResult.Success(
                                LoggedInUser(
                                    user!!.uid,
                                    user.email!!
                                )
                            )
                        )
                    } else if (task.exception != null) {
                        var message = "Error logging in"
                        if(task.exception is FirebaseAuthInvalidUserException){
                            message = "Email does not exist. Sign up to continue."
                        }
                        else if(task.exception is FirebaseAuthInvalidCredentialsException ){
                            message = "Invalid password"
                        }
                        listener.onFailed(AuthResult.Error(IOException(message)))
                    }
                }
        }
        catch (e: Exception){
            listener.onFailed(AuthResult.Error(IOException(e)))
        }
    }

    fun loginWithGoogle(idToken: String, listener: AuthCompleteListener) {
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = firebaseAuth.currentUser
                        val isNewUser = task.result!!.additionalUserInfo!!.isNewUser
                        listener.onSuccess(
                            AuthResult.Success(
                                LoggedInUser(
                                    user!!.uid,
                                    user.email!!,
                                    isNewUser
                                )
                            )
                        )
                    } else if (task.exception != null) {
                        var message = "Error logging in"
                        if(task.exception is FirebaseAuthInvalidUserException){
                            message = "Email does not exist. Sign up to continue."
                        }
                        else if(task.exception is FirebaseAuthInvalidCredentialsException ){
                            message = "Invalid password"
                        }
                        listener.onFailed(AuthResult.Error(IOException(message)))
                    }
                }
        }
        catch (e: Exception){
            listener.onFailed(AuthResult.Error(IOException(e)))
        }
    }

    fun sendPasswordResetEmail(email: String, listener: ResetCompleteListener){
        try{
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    Log.println(Log.ASSERT, "Password Reset", "Email sent")
                    listener.onSuccess(
                        AuthResult.Success(
                            ResetEmail(true)
                        )
                    )
                }
                else if (task.exception != null) {
                    val message = "Error sending reset link."
                    listener.onFailed(AuthResult.Error(IOException(message)))

                }
            }
        }
        catch (e: Exception){
            listener.onFailed(AuthResult.Error(IOException("Error sending reset link.")))
        }
    }

    fun logout() {
        firebaseAuth.signOut()
    }

}