package pamo.bmicalc_kotlin.ui.calculate

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import pamo.bmicalc_kotlin.R

class BmiResultsFragment : Fragment() {
    private var cViewModel: CalculateViewModel? = null
    private var tvBmiResult: TextView? = null
    private var tvEnergyResult: TextView? = null
    private var tvCategory: TextView? = null
    private var tvRecipe: TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_bmi_results, container, false)
        cViewModel = ViewModelProvider(requireActivity()).get(CalculateViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvBmiResult = view.findViewById<View>(R.id.bmiResults_bmi_text) as TextView
        tvEnergyResult = view.findViewById<View>(R.id.bmiResults_energy_text) as TextView
        tvCategory = view.findViewById<View>(R.id.bmiResults_category_text) as TextView
        tvRecipe = view.findViewById<View>(R.id.bmiResults_recipe_text) as TextView
        updateResults(
                cViewModel!!.getStringMapValueByKey("bmiResult"),
                cViewModel!!.getStringMapValueByKey("energyResult"),
                cViewModel!!.getStringMapValueByKey("bmiCategory"),
                cViewModel!!.getStringMapValueByKey("bmiRecipe")
        )
    }

    @SuppressLint("SetTextI18n")
    fun updateResults(bmiValue: String?, energyValue: String?, bmiCategory: String?, bmiRecipe: String?) {
        tvBmiResult!!.text = bmiValue
        tvCategory!!.text = bmiCategory
        tvEnergyResult!!.text = energyValue
        tvRecipe!!.text = bmiRecipe
    }
}