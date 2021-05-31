package android.josephhowerton.travelingsalesman.ui.auth.register

import android.animation.Animator
import android.animation.ObjectAnimator
import android.josephhowerton.travelingsalesman.R
import android.josephhowerton.travelingsalesman.databinding.FragmentRegisterBinding
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

class RegisterFragment : Fragment(), Animator.AnimatorListener {
    private lateinit var binding:FragmentRegisterBinding
    private lateinit var viewModel:RegisterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)



        init()

        return binding.root
    }

    private fun init(){
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.navigateBack.observe(viewLifecycleOwner, {
            if (it) findNavController().navigate(R.id.action_navigation_register_to_navigation_auth)
        })

        viewModel.signUpWithEmail.observe(viewLifecycleOwner, {
        })

        viewModel.signUpWithGoogle.observe(viewLifecycleOwner, {

        })
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

        ObjectAnimator.ofFloat(binding.textViewName, "alpha", 0f)
                .apply {
                    duration = 500
                    startDelay = 250
                    start()
                }

        ObjectAnimator.ofFloat(binding.textViewEmail, "alpha", 0f)
                .apply {
                    duration = 500
                    startDelay = 250
                    start()
                }

        ObjectAnimator.ofFloat(binding.textViewPassword, "alpha", 0f)
                .apply {
                    duration = 500
                    startDelay = 250
                    start()
                }

        ObjectAnimator.ofFloat(binding.textViewOr, "alpha", 0f)
                .apply {
                    duration = 500
                    startDelay = 250
                    start()
                }

        ObjectAnimator.ofFloat(binding.viewEnd, "alpha", 0f)
                .apply {
                    duration = 500
                    startDelay = 250
                    start()
                }

        ObjectAnimator.ofFloat(binding.viewStart, "alpha", 0f)
                .apply {
                    duration = 500
                    startDelay = 250
                    start()
                }

        ObjectAnimator.ofFloat(binding.textViewSignIn, "alpha", 0f)
                .apply {
                    duration = 500
                    startDelay = 250
                    start()
                }.addListener(this)
    }


    override fun onAnimationStart(animation: Animator?) {

    }

    override fun onAnimationEnd(animation: Animator?) {

    }

    override fun onAnimationCancel(animation: Animator?) {

    }

    override fun onAnimationRepeat(animation: Animator?) {

    }
}