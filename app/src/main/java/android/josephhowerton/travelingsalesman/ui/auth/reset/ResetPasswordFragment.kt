package android.josephhowerton.travelingsalesman.ui.auth.reset

import android.animation.Animator
import android.animation.ObjectAnimator
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.josephhowerton.travelingsalesman.R
import android.josephhowerton.travelingsalesman.databinding.FragmentResetPasswordBinding
import android.josephhowerton.travelingsalesman.ui.success.SuccessViewModel
import android.josephhowerton.travelingsalesman.ui.success.SuccessViewModelFactory
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateOvershootInterpolator
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

class ResetPasswordFragment : Fragment(), Animator.AnimatorListener {
    private lateinit var binding: FragmentResetPasswordBinding
    private lateinit var viewModel: ResetPasswordViewModel
    private lateinit var successViewModel: SuccessViewModel


    private val TAG = ResetPasswordFragment::class.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_reset_password, container, false)


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory = ResetPasswordViewModelFactory(requireActivity().application)
        val successViewModelFactory = SuccessViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResetPasswordViewModel::class.java)
        successViewModel = ViewModelProvider(this, successViewModelFactory).get(SuccessViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initTextWatcher()

        binding.screenSuccess.btnEmailAuth.setOnClickListener {
            viewModel.destination = R.id.action_navigation_reset_password_to_navigation_login
            animateSuccessScreen()
        }

        viewModel.resultEmail.observe(viewLifecycleOwner,  Observer { resetResult ->
            resetResult ?: return@Observer

            resetResult.error?.let {
                showResetFailed(it)
            }

            resetResult.message?.let {
                showResetFailed(it)
            }

            resetResult.success?.let {
                showResetSuccess(it)
            }
        })

        viewModel.resetFormState.observe(viewLifecycleOwner, Observer
            {registerFormState ->

                if (registerFormState == null){
                    return@Observer
                }

                binding.btnResetPassword.isEnabled = registerFormState.isDataValid

                registerFormState.emailError?.let {
                    binding.editTextEmail.error = getString(it)
                }
            }
        )

        successViewModel.continueClick.observe(viewLifecycleOwner, {
            successViewModel.navigate(it, R.id.action_navigation_reset_password_to_navigation_login)
        })

        successViewModel.navigate.observe(viewLifecycleOwner, {
            if(it != null) findNavController().navigate(it)
        })

        viewModel.animate.observe(viewLifecycleOwner, {
            animate()
        })
    }

    private fun initTextWatcher(){
        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                viewModel.loginDataChanged()
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                viewModel.loginDataChanged()
            }
        }

        binding.editTextEmail.addTextChangedListener(afterTextChangedListener)
    }


    private fun animate(){
        ObjectAnimator.ofFloat(binding.imageViewClose, "translationY", 10000f)
            .apply {
                duration = 750
                start()
            }

        ObjectAnimator.ofFloat(binding.textViewGreeting, "translationX", -20000f)
            .apply {
                duration = 750
                start()
            }

        ObjectAnimator.ofFloat(binding.editTextEmail, "translationX", 20000f)
            .apply {
                duration = 550
                startDelay = 200
                start()
            }

        ObjectAnimator.ofFloat(binding.btnResetPassword, "translationY", 20000f)
            .apply {
                duration = 550
                startDelay = 200
                start()
            }.addListener(this)
    }

    private fun animateSuccessScreen(){
        val animationUtils = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_bounce)

        ObjectAnimator.ofFloat(binding.screenSuccess.btnEmailAuth, "translationY", 20000f)
            .apply {
                duration = 250
                start()
            }

        ObjectAnimator.ofFloat(binding.screenSuccess.imageViewCheckMark, "translationY", -10f,-20f, -40f, -60f, -80f,-100f,
            80f, 60f, 40f, 20f, 0f)
            .apply {
                interpolator = FastOutSlowInInterpolator()
                duration = 800
                start()
            }

        ObjectAnimator.ofFloat(binding.screenSuccess.imageViewVeryHappy, "translationY", -10f,-20f, -40f, -60f, -80f,-100f,
            80f, 60f, 40f, 20f, 0f)
            .apply {
                interpolator = FastOutSlowInInterpolator()
                duration = 800
                startDelay = 150
                start()
            }

        ObjectAnimator.ofFloat(binding.screenSuccess.imageViewTakeOff, "translationY", -10f,-20f, -40f, -60f, -80f,-100f,
            80f, 60f, 40f, 20f, 0f)
            .apply {
                interpolator = FastOutSlowInInterpolator()
                duration = 800
                startDelay = 300
                start()
            }.addListener(this)
    }

    private fun navigate(whereTo: Int){
        findNavController().navigate(whereTo)
    }

    private fun showResetSuccess(success: Boolean) {
        val welcome = "${getString(R.string.reset)} ${success}"
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showResetFailed(errorString: String) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    private fun showResetFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    override fun onAnimationEnd(animation: Animator?) {
        if(viewModel.destination != null) navigate(viewModel.destination!!)
    }

    override fun onAnimationStart(animation: Animator?) {}
    override fun onAnimationCancel(animation: Animator?) {}
    override fun onAnimationRepeat(animation: Animator?) {}
}