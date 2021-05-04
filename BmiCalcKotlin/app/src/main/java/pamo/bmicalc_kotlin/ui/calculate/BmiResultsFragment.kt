package pamo.bmicalc_kotlin.ui.calculate

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pamo.bmicalc_kotlin.R

class BmiResultsFragment : Fragment() {

    companion object {
        fun newInstance() = BmiResultsFragment()
    }

    private lateinit var viewModel: BmiResultsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bmi_results, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BmiResultsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}