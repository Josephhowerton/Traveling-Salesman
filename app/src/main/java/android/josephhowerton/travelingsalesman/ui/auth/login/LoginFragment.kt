package android.josephhowerton.travelingsalesman.ui.auth.login

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
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)

        loginViewModel.navigateBack.observe(viewLifecycleOwner, {
            loginViewModel.navigateBack(it)
        })

        loginViewModel.whereTo.observe(viewLifecycleOwner, {
            if(it != null){
                findNavController().navigate(it)
            }
        })

        loginViewModel.loginFormState.observe(viewLifecycleOwner,
                Observer { loginFormState ->
                    if (loginFormState == null) {
                        return@Observer
                    }
                    binding.login.isEnabled = loginFormState.isDataValid
                    loginFormState.usernameError?.let {
                        binding.username.error = getString(it)
                    }
                    loginFormState.passwordError?.let {
                        binding.password.error = getString(it)
                    }
                })

        loginViewModel.loginResult.observe(viewLifecycleOwner,
                Observer { loginResult ->
                    loginResult ?: return@Observer
                    binding.loading.visibility = View.GONE
                    loginResult.error?.let {
                        showLoginFailed(it)
                    }
                    loginResult.success?.let {
                        updateUiWithUser(it)
                    }
                })

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                        binding.username.text.toString(),
                        binding.password.text.toString()
                )
            }
        }
        binding.username.addTextChangedListener(afterTextChangedListener)
        binding.password.addTextChangedListener(afterTextChangedListener)
        binding.password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                        binding.username.text.toString(),
                        binding.password.text.toString()
                )
            }
            false
        }

        binding.login.setOnClickListener {
            binding.loading.visibility = View.VISIBLE
            loginViewModel.login(
                    binding.username.text.toString(),
                    binding.password.text.toString()
            )
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.displayName
        // TODO : initiate successful logged in experience
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, welcome, Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }
}