package android.josephhowerton.travelingsalesman.ui.splash

import android.content.Intent
import android.josephhowerton.travelingsalesman.ui.auth.AuthActivity
import android.josephhowerton.travelingsalesman.ui.main.MainActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

class SplashActivity : AppCompatActivity() {
    private lateinit var viewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        isUserSignedIn()
        navigationToAuth()
        navigationToMain()
    }

    private fun isUserSignedIn(){
        viewModel.isUserSignedIn.observe(this, {
            viewModel.navigate(it)
        })
    }

    private fun navigationToAuth(){
        viewModel.sendToAuth.observe(this, {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    private fun navigationToMain(){
        viewModel.sendToMain.observe(this, {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

}