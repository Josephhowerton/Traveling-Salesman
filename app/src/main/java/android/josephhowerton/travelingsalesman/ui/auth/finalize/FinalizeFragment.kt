package android.josephhowerton.travelingsalesman.ui.auth.finalize

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.josephhowerton.travelingsalesman.R
import android.josephhowerton.travelingsalesman.databinding.FragmentFinalizeBinding
import android.josephhowerton.travelingsalesman.databinding.FragmentLocationBinding
import android.josephhowerton.travelingsalesman.ui.main.MainActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

class FinalizeFragment : Fragment(), Animator.AnimatorListener   {
    private lateinit var binding: FragmentFinalizeBinding
    private lateinit var viewModel: FinalizeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_finalize, container, false)



        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = FinalizeViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(FinalizeViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.finishInitialization.observe(viewLifecycleOwner, viewModel::onInitializationFinished)

        viewModel.animate.observe(viewLifecycleOwner, {
            animate()
        })
    }

    private fun animate(){
        ObjectAnimator.ofFloat(binding.loading, "translationY", 20000f)
            .apply {
                duration = 500
                start()
            }

        ObjectAnimator.ofFloat(binding.textViewMessage, "alpha", 0f)
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
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

}