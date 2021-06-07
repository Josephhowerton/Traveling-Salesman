package android.josephhowerton.travelingsalesman.ui.auth.location

import android.animation.Animator
import android.animation.ObjectAnimator
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.josephhowerton.travelingsalesman.R
import android.josephhowerton.travelingsalesman.databinding.FragmentLocationBinding
import android.josephhowerton.travelingsalesman.ui.auth.welcome.WelcomeViewModel
import android.view.animation.OvershootInterpolator
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

class LocationFragment : Fragment(), Animator.AnimatorListener {
    private lateinit var binding: FragmentLocationBinding
    private lateinit var viewModel: LocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_location, container, false)


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = LocationViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(LocationViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.continueStep.observe(viewLifecycleOwner, viewModel::continueStep)
        viewModel.skipStep.observe(viewLifecycleOwner, viewModel::skipToNext)

        viewModel.animate.observe(viewLifecycleOwner, {
            if(it) animate()
        })
    }

    private fun animate(){
        ObjectAnimator.ofFloat(binding.btnContinue, "translationY", -25f, 20000f)
            .apply {
                duration = 500
                startDelay = 250
                interpolator = OvershootInterpolator()
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
            }.addListener(this)
    }

    override fun onAnimationStart(animation: Animator?) {
    }

    override fun onAnimationEnd(animation: Animator?) {
        navigate()
    }

    override fun onAnimationCancel(animation: Animator?) {
    }

    override fun onAnimationRepeat(animation: Animator?) {
    }

    private fun navigate(){
        findNavController().navigate(viewModel.destination!!)
    }

}