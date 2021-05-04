package pamo.bmicalc.ui.calculate;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import pamo.bmicalc.R;

public class CalculateFragment extends Fragment {

    private CalculateViewModel mViewModel;
    private EditText inputHeight, inputWeight, inputAge;
    private RadioGroup radioGroup_Gender;
    private String selectedGender, calculatedCategory, recommendedRecipe, calculatedEnergyResult, calculatedBmiResult;
    private BmiResultsFragment bmiResultsFragment = new BmiResultsFragment();

    public static CalculateFragment newInstance() {
        return new CalculateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate, container, false);
        mViewModel = new ViewModelProvider(getActivity()).get(CalculateViewModel.class);
        Log.v("CalculateFragment", "Entered -> onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Log.v("CalculateFragment", "Entered -> onViewCreated");

        inputHeight = (EditText) view.findViewById(R.id.calculate_input_height);
        inputWeight = (EditText) view.findViewById(R.id.calculate_input_weight);
        inputAge = (EditText) view.findViewById(R.id.calculate_input_age);
        radioGroup_Gender = (RadioGroup) view.findViewById(R.id.calculate_radioGroup);

        Button btn_submit = (Button) view.findViewById(R.id.calculate_button_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radio_SelectedGender = (RadioButton) view.findViewById(radioGroup_Gender.getCheckedRadioButtonId());
                selectedGender = radio_SelectedGender.getText().toString();

                calculatedEnergyResult = calculateEnergy();
                mViewModel.addStringDataToMap("energyResult", calculatedEnergyResult);

                calculatedBmiResult = calculateBmi();
                mViewModel.addStringDataToMap("bmiResult", calculatedBmiResult);

                calculatedCategory = checkCategory();
                mViewModel.addStringDataToMap("bmiCategory", calculatedCategory);
                mViewModel.addStringDataToMap("bmiRecipe", recommendedRecipe);

                Log.v("CalculateBmi", "Calculated BMI: " + calculatedBmiResult + "; Calculated Energy: " + calculatedEnergyResult);
                switchFragment(bmiResultsFragment);
            }
        });
    }

    public String calculateBmi() {
        double height = Double.parseDouble(inputHeight.getText().toString());
        double weight = Double.parseDouble(inputWeight.getText().toString());
        return Double.toString(Math.round(weight / Math.pow(((double) height / 100), 2)));
    }

    public String checkCategory() {
        double bmiFromString = Double.parseDouble(calculatedBmiResult);
        String category;

        if (bmiFromString < 15) {
            category = getResources().getString(R.string.bmiresults_category_underweight3);
            recommendedRecipe = getResources().getString(R.string.bmiresults_recipe_underweight);
        } else if (15 <= bmiFromString && bmiFromString < 16) {
            category = getResources().getString(R.string.bmiresults_category_underweight2);
            recommendedRecipe = getResources().getString(R.string.bmiresults_recipe_underweight);
        } else if (16 <= bmiFromString && bmiFromString < 18.5) {
            category = getResources().getString(R.string.bmiresults_category_underweight1);
            recommendedRecipe = getResources().getString(R.string.bmiresults_recipe_underweight);
        } else if (18.5 <= bmiFromString && bmiFromString < 25) {
            category = getResources().getString(R.string.bmiresults_category_normal);
            recommendedRecipe = getResources().getString(R.string.bmiresults_recipe_normal);
        } else if (25 <= bmiFromString && bmiFromString < 30) {
            category = getResources().getString(R.string.bmiresults_category_overweight);
            recommendedRecipe = getResources().getString(R.string.bmiresults_recipe_overweight);
        } else if (30 <= bmiFromString && bmiFromString < 35) {
            category = getResources().getString(R.string.bmiresults_category_obese1);
            recommendedRecipe = getResources().getString(R.string.bmiresults_recipe_overweight);
        } else if (35 <= bmiFromString && bmiFromString < 40) {
            category = getResources().getString(R.string.bmiresults_category_obese2);
            recommendedRecipe = getResources().getString(R.string.bmiresults_recipe_overweight);
        } else {
            category = getResources().getString(R.string.bmiresults_category_obese3);
            recommendedRecipe = getResources().getString(R.string.bmiresults_recipe_overweight);
        }
        return category;
    }

    public String calculateEnergy() {
        double height = Double.parseDouble(inputHeight.getText().toString());
        double weight = Double.parseDouble(inputWeight.getText().toString());
        double age = Double.parseDouble(inputAge.getText().toString());
        double result;

        if (selectedGender == "Male"){
            result = 66.47 + (13.7 * weight) + (5.0 * height) - (6.76 * age);
        } else {
            result = 655.1 + (9.567 * weight) + (1.85 * height) - (4.68 * age);
        }
        return Double.toString(Math.round(result));
    }

    public void switchFragment(Fragment frag) {
        FragmentTransaction ftx = getParentFragmentManager().beginTransaction();
        ftx.replace(R.id.nav_host_fragment, frag).addToBackStack("CalculateFragment").commit();
    }
}