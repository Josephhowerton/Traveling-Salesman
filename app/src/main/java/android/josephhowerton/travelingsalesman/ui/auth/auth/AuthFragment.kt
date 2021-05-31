package android.josephhowerton.travelingsalesman.ui.auth.auth

import android.animation.Animator
import android.animation.ObjectAnimator
import android.graphics.SurfaceTexture
import android.josephhowerton.travelingsalesman.R
import android.josephhowerton.travelingsalesman.databinding.FragmentAuthBinding
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.core.animation.addListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import java.io.IOException
import kotlin.properties.Delegates


class AuthFragment : Fragment(), Animator.AnimatorListener {
    private lateinit var binding:FragmentAuthBinding
    private lateinit var viewModel:AuthViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_auth, container, false)

        init()


        return binding.root
    }

    private fun init(){
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.signInWithEmail.observe(viewLifecycleOwner, {
            viewModel.destination = R.id.navigation_login
            viewModel.signInWithEmail(it)
        })

        viewModel.signInWithGoogle.observe(viewLifecycleOwner, {
            viewModel.destination = R.id.navigation_login
            viewModel.signInWithGoogle(it)
        })

        viewModel.signUp.observe(viewLifecycleOwner, {
            viewModel.destination = R.id.action_navigation_auth_to_navigation_register
            viewModel.signUp(it)
        })

        viewModel.animate.observe(viewLifecycleOwner, {
            animate()
        })

        viewModel.navigate.observe(viewLifecycleOwner, {
            navigate(it)
        })

    }

    private fun navigate(whereTo: Int?){
        if(whereTo != null) {
            findNavController().navigate(whereTo)
        }
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
        viewModel.navigate()
    }

    override fun onAnimationCancel(animation: Animator?) {
    }

    override fun onAnimationRepeat(animation: Animator?) {
    }
}