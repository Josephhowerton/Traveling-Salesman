package android.josephhowerton.travelingsalesman.ui.auth.push

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.josephhowerton.travelingsalesman.R

class PushFragment : Fragment() {

    companion object {
        fun newInstance() = PushFragment()
    }

    private lateinit var viewModel: PushViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_push, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PushViewModel::class.java)
        // TODO: Use the ViewModel
    }

}