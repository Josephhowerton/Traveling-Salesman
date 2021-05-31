package android.josephhowerton.travelingsalesman.ui.main.plans

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.josephhowerton.travelingsalesman.R

class PlansFragment : Fragment() {

    companion object {
        fun newInstance() = PlansFragment()
    }

    private lateinit var viewModel: PlansViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_plans, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlansViewModel::class.java)
        // TODO: Use the ViewModel
    }

}