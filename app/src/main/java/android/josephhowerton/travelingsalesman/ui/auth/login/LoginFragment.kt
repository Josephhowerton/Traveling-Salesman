package android.josephhowerton.travelingsalesman.ui.auth.login

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo

import android.josephhowerton.travelingsalesman.R
import android.josephhowerton.travelingsalesman.databinding.FragmentLoginBinding
import android.josephhowerton.travelingsalesman.ui.main.MainActivity
import android.util.Log
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment(), Animator.AnimatorListener {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initFormState()
        initTextWatcher()
    }


    private fun init(){
        viewModel = ViewModelProvider(this, LoginViewModelFactory(requireActivity().application)).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.animate.observe(viewLifecycleOwner, {
            animate()
        })
    }

    private fun animate(){
        ObjectAnimator.ofFloat(binding.imageViewClose, "translationY", 10000f)
            .apply {
                duration = 750
                start()
            }

        ObjectAnimator.ofFloat(binding.editTextEmail, "translationX", 20000f)
            .apply {
                duration = 500
                start()
            }

        ObjectAnimator.ofFloat(binding.editTextPassword, "translationX", -20000f)
            .apply {
                duration = 500
                startDelay = 250
                start()
            }

        ObjectAnimator.ofFloat(binding.btnLogin,  "translationX", 20000f)
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

        ObjectAnimator.ofFloat(binding.textViewResetPassword, "alpha", 0f)
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
            .addListener(this)
    }

    private fun initFormState(){
        viewModel.loginFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                binding.btnLogin.isEnabled = loginFormState.isDataValid
                loginFormState.emailError?.let {
                    binding.editTextEmail.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    binding.editTextPassword.error = getString(it)
                }
            })

        viewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                binding.loading.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.message?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    updateUiWithUser(it)
                }
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
        binding.editTextPassword.addTextChangedListener(afterTextChangedListener)
        binding.editTextPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.loginWithEmail()
            }
            false
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = "${getString(R.string.welcome)} ${model.displayName}"
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(errorString: String) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    private fun navigate(){
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun navigate(whereTo: Int){
        findNavController().navigate(whereTo)
    }

    override fun onAnimationStart(animation: Animator?) {
    }

    override fun onAnimationEnd(animation: Animator?) {
        if(viewModel.destination == null) navigate()
        else navigate(viewModel.destination!!)
    }

    override fun onAnimationCancel(animation: Animator?) {
    }

    override fun onAnimationRepeat(animation: Animator?) {
    }
}