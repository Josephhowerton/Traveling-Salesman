package android.josephhowerton.travelingsalesman.ui.auth.finalize

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.josephhowerton.travelingsalesman.R

class FinalizeFragment : Fragment() {

    companion object {
        fun newInstance() = FinalizeFragment()
    }

    private lateinit var viewModel: FinalizeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_finalize, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FinalizeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}