package android.josephhowerton.travelingsalesman.ui.auth.interest

import android.animation.Animator
import android.animation.ObjectAnimator
import android.josephhowerton.travelingsalesman.R
import android.josephhowerton.travelingsalesman.data.foursquare.FoursquareCategory
import android.josephhowerton.travelingsalesman.databinding.FragmentPickInterestBinding
import android.josephhowerton.travelingsalesman.ui.auth.finalize.FinalizeViewModelFactory
import android.josephhowerton.travelingsalesman.ui.auth.welcome.WelcomeViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class PickInterestFragment : Fragment(), Animator.AnimatorListener, PickInterestAdapter.CategoryClickListener   {
    private lateinit var binding: FragmentPickInterestBinding
    private lateinit var viewModel: PickInterestViewModel
    private lateinit var list: ArrayList<FoursquareCategory>
    private lateinit var adapter: PickInterestAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_pick_interest, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = PickInterestViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(PickInterestViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.continueStep.observe(viewLifecycleOwner, viewModel::continueStep)

        viewModel.animate.observe(viewLifecycleOwner, {
            if(it) animate()
        })

        viewModel.foursquareTest.observe(viewLifecycleOwner, {
            list = it.response.categories as ArrayList<FoursquareCategory>
            adapter = PickInterestAdapter(list, this)
            val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = layoutManager
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

        ObjectAnimator.ofFloat(binding.recyclerView, "alpha", 0f)
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

    override fun onCategoryClick(category: FoursquareCategory) {
        if(!category.categories.isEmpty()){
            val indexOf = list.indexOf(category)
            list.addAll(category.categories)
            adapter.notifyItemRangeChanged(indexOf+1, category.categories.size)
        }
    }
}