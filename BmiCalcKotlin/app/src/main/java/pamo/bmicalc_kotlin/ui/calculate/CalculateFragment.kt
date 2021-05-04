package pamo.bmicalc_kotlin.ui.calculate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pamo.bmicalc_kotlin.R

class CalculateFragment : Fragment() {

    private lateinit var calculateViewModel: CalculateViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        calculateViewModel =
                ViewModelProvider(this).get(CalculateViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_calculate, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        calculateViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}