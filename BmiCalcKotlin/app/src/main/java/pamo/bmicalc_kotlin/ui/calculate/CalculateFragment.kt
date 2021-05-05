package pamo.bmicalc_kotlin.ui.calculate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pamo.bmicalc_kotlin.R
import kotlin.math.pow
import kotlin.math.roundToInt

class CalculateFragment : Fragment() {
    private var mViewModel: CalculateViewModel? = null
    private var inputHeight: EditText? = null
    private var inputWeight: EditText? = null
    private var inputAge: EditText? = null
    private var radioGroupGender: RadioGroup? = null
    private var selectedGender: String? = null
    private var calculatedCategory: String? = null
    private var recommendedRecipe: String? = null
    private var calculatedEnergyResult: String? = null
    private var calculatedBmiResult: String? = null
    private val bmiResultsFragment = BmiResultsFragment()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_calculate, container, false)
        mViewModel = ViewModelProvider(requireActivity()).get(CalculateViewModel::class.java)
        Log.v("CalculateFragment", "Entered -> onCreateView")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.v("CalculateFragment", "Entered -> onViewCreated")
        inputHeight = view.findViewById<View>(R.id.calculate_input_height) as EditText
        inputWeight = view.findViewById<View>(R.id.calculate_input_weight) as EditText
        inputAge = view.findViewById<View>(R.id.calculate_input_age) as EditText
        radioGroupGender = view.findViewById<View>(R.id.calculate_radioGroup) as RadioGroup
        val btnSubmit = view.findViewById<View>(R.id.calculate_button_submit) as Button
        btnSubmit.setOnClickListener {
            val radioSelectedGender = view.findViewById<View>(radioGroupGender!!.checkedRadioButtonId) as RadioButton
            selectedGender = radioSelectedGender.text.toString()
            calculatedEnergyResult = calculateEnergy()
            mViewModel!!.addStringDataToMap("energyResult", calculatedEnergyResult!!)
            calculatedBmiResult = calculateBmi()
            mViewModel!!.addStringDataToMap("bmiResult", calculatedBmiResult!!)
            calculatedCategory = checkCategory()
            mViewModel!!.addStringDataToMap("bmiCategory", calculatedCategory!!)
            mViewModel!!.addStringDataToMap("bmiRecipe", recommendedRecipe!!)
            Log.v("CalculateBmi", "Calculated BMI: $calculatedBmiResult; Calculated Energy: $calculatedEnergyResult")
            switchFragment(bmiResultsFragment)
        }
    }

    private fun calculateBmi(): String {
        val height = inputHeight!!.text.toString().toDouble()
        val weight = inputWeight!!.text.toString().toDouble()
        return (weight / (height / 100).pow(2.0)).roundToInt().toDouble().toString()
    }

    private fun checkCategory(): String {
        val bmiFromString = calculatedBmiResult!!.toDouble()
        val category: String
        if (bmiFromString < 15) {
            category = resources.getString(R.string.bmiresults_category_underweight3)
            recommendedRecipe = resources.getString(R.string.bmiresults_recipe_underweight)
        } else if (15 <= bmiFromString && bmiFromString < 16) {
            category = resources.getString(R.string.bmiresults_category_underweight2)
            recommendedRecipe = resources.getString(R.string.bmiresults_recipe_underweight)
        } else if (16 <= bmiFromString && bmiFromString < 18.5) {
            category = resources.getString(R.string.bmiresults_category_underweight1)
            recommendedRecipe = resources.getString(R.string.bmiresults_recipe_underweight)
        } else if (18.5 <= bmiFromString && bmiFromString < 25) {
            category = resources.getString(R.string.bmiresults_category_normal)
            recommendedRecipe = resources.getString(R.string.bmiresults_recipe_normal)
        } else if (25 <= bmiFromString && bmiFromString < 30) {
            category = resources.getString(R.string.bmiresults_category_overweight)
            recommendedRecipe = resources.getString(R.string.bmiresults_recipe_overweight)
        } else if (30 <= bmiFromString && bmiFromString < 35) {
            category = resources.getString(R.string.bmiresults_category_obese1)
            recommendedRecipe = resources.getString(R.string.bmiresults_recipe_overweight)
        } else if (35 <= bmiFromString && bmiFromString < 40) {
            category = resources.getString(R.string.bmiresults_category_obese2)
            recommendedRecipe = resources.getString(R.string.bmiresults_recipe_overweight)
        } else {
            category = resources.getString(R.string.bmiresults_category_obese3)
            recommendedRecipe = resources.getString(R.string.bmiresults_recipe_overweight)
        }
        return category
    }

    private fun calculateEnergy(): String {
        val height = inputHeight!!.text.toString().toDouble()
        val weight = inputWeight!!.text.toString().toDouble()
        val age = inputAge!!.text.toString().toDouble()
        val result: Double = if (selectedGender === "Male") {
            66.47 + 13.7 * weight + 5.0 * height - 6.76 * age
        } else {
            655.1 + 9.567 * weight + 1.85 * height - 4.68 * age
        }
        return result.roundToInt().toDouble().toString()
    }

    private fun switchFragment(frag: Fragment?) {
        val ftx = parentFragmentManager.beginTransaction()
        ftx.replace(R.id.nav_host_fragment, frag!!).addToBackStack("CalculateFragment").commit()
    }
}