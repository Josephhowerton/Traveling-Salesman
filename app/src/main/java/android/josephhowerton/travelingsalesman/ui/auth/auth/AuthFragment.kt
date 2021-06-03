package android.josephhowerton.travelingsalesman.ui.auth.auth

import android.view.*
import android.os.Bundle
import android.animation.Animator
import androidx.fragment.app.Fragment
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.databinding.DataBindingUtil
import android.josephhowerton.travelingsalesman.R
import android.josephhowerton.travelingsalesman.app.Config
import androidx.navigation.fragment.findNavController
import android.josephhowerton.travelingsalesman.databinding.FragmentAuthBinding
import android.josephhowerton.travelingsalesman.ui.auth.login.LoggedInUserView
import android.josephhowerton.travelingsalesman.ui.main.MainActivity
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class AuthFragment : Fragment(), Animator.AnimatorListener {
    private lateinit var binding:FragmentAuthBinding
    private lateinit var viewModel:AuthViewModel
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_auth, container, false)

        init()


        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.registerResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer

                loginResult.error?.let {
                    showLoginFailed(it)
                }

                loginResult.message?.let {
                    showLoginFailed(it)
                }

                loginResult.success?.let {
                    showLoginSuccess(it)
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == Config.RC_SIGN_IN) {
            viewModel.signInWithGoogle(data)
        }
    }

    private fun init(){
        val viewModelFactory = AuthViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AuthViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.signInWithEmail.observe(viewLifecycleOwner, {
            viewModel.signInWithEmail(it)
        })

        viewModel.signInWithGoogle.observe(viewLifecycleOwner, {
            if(it) signInWithGoogle()
        })

        viewModel.signUp.observe(viewLifecycleOwner, {
            viewModel.signUp(it)
        })

        viewModel.animate.observe(viewLifecycleOwner, {
            animate()
        })

        viewModel.navigate.observe(viewLifecycleOwner, {
            navigate(it)
        })

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.google_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity().application, gso)

    }

    private fun signInWithGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, Config.RC_SIGN_IN)
    }

    private fun animate(){
        ObjectAnimator.ofFloat(binding.btnEmailAuth, "translationX", 20000f)
                .apply {
                    duration = 500
                    start()
                }

        ObjectAnimator.ofFloat(binding.btnGoogleAuth, "translationX", -20000f)
                .apply {
                    duration = 500
                    startDelay = 250
                    start()
                }

        ObjectAnimator.ofFloat(binding.textViewMessage, "alpha", 0f)
                .apply {
                    duration = 500
                    startDelay = 250
                    start()
                }

        ObjectAnimator.ofFloat(binding.textViewGreeting, "alpha", 0f)
                .apply {
                    duration = 500
                    startDelay = 250
                    start()
                }

        ObjectAnimator.ofFloat(binding.textViewSignUp, "alpha", 0f)
                .apply {
                    duration = 500
                    startDelay = 250
                    start()
                }
                .addListener(this)
    }

    override fun onAnimationStart(animation: Animator?) {
    }

    override fun onAnimationEnd(animation: Animator?) {
        when(viewModel.destination){
            R.id.action_navigation_auth_to_navigation_welcome -> checkIfNewUser()
            else -> viewModel.navigate()
        }
    }

    override fun onAnimationCancel(animation: Animator?) {
    }

    override fun onAnimationRepeat(animation: Animator?) {
    }

    private fun checkIfNewUser(){
        if(viewModel.isNewUser)
            navigate(R.id.action_navigation_auth_to_navigation_welcome)
        else
            navigate()
    }

    private fun navigate(whereTo: Int?){
        if(whereTo != null) {
            findNavController().navigate(whereTo)
        }
    }

    private fun navigate(){
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun showLoginSuccess(model: LoggedInUserView) {
        val welcome = "${getString(R.string.welcome)} ${model.displayName}"
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(errorString: String) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
}