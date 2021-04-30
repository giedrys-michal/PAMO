package pamo.bmicalc.ui.calculate;

import androidx.fragment.app.FragmentManager;
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

import pamo.bmicalc.R;

public class CalculateFragment extends Fragment {

    private CalculateViewModel mViewModel;
    private EditText inputHeight, inputWeight, inputAge;
    private RadioGroup radioGroup_Gender;
    private double calculatedBmiResult, calculatedEnergyResult;
    private String selectedGender;
    private BmiResultsFragment bmiResultsFragment = new BmiResultsFragment();

    public static CalculateFragment newInstance() {
        return new CalculateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate, container, false);
        mViewModel = new ViewModelProvider(getActivity()).get(CalculateViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

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
                Log.v("CalculateBmi", "Selected gender: " + selectedGender);
                calculatedEnergyResult = calculateEnergy();
                mViewModel.addDataToMap("energyResult", calculatedEnergyResult);

                calculatedBmiResult = calculateBmi();
                mViewModel.addDataToMap("bmiResult", calculatedBmiResult);

                Log.v("CalculateBmi", "Calculated BMI: " + calculatedBmiResult + "; Calculated Energy: " + calculatedEnergyResult);
                switchFragment(bmiResultsFragment);
            }
        });
    }

    public double calculateBmi() {
        double height = Double.parseDouble(inputHeight.getText().toString());
        double weight = Double.parseDouble(inputWeight.getText().toString());
        return Math.round(weight / Math.pow(((double) height / 100), 2));
    }

    public double calculateEnergy() {
        double height = Double.parseDouble(inputHeight.getText().toString());
        double weight = Double.parseDouble(inputWeight.getText().toString());
        double age = Double.parseDouble(inputAge.getText().toString());
        double result;

        if (selectedGender == "Male"){
            result = 66.47 + (13.7 * weight) + (5.0 * height) - (6.76 * age);
        } else {
            result = 655.1 + (9.567 * weight) + (1.85 * height) - (4.68 * age);
        }

        Log.v("CalculateBmi", "Selected gender: " + result);
        return result;
    }

    public void switchFragment(Fragment frag) {
        Log.v("CalculateBMI", "Inside fragment switch");
        FragmentManager fm = getParentFragmentManager();
        fm.beginTransaction().replace(R.id.nav_host_fragment, frag).commit();
    }


}