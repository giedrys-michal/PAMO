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
    private int calculatedBmiResult;
    private double calculatedEnergyResult;
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
                calculatedEnergyResult = calculateEnergy();

                calculatedBmiResult = calculateBmi();
                Log.v("CalculateBmi", "Calculated BMI: " + calculatedBmiResult);
                mViewModel.addDataToMap("bmiResult", calculatedBmiResult);
                int getImportedResult = mViewModel.getMapValueByKey("bmiResult");
                Log.v("CalculateBmi", "Imported result: " + getImportedResult);
                switchFragment(bmiResultsFragment);
            }
        });
    }

    public int calculateBmi() {
        int height = Integer.parseInt(inputHeight.getText().toString());
        int weight = Integer.parseInt(inputWeight.getText().toString());
        return (int) Math.round(weight / Math.pow(((double) height / 100), 2));
    }

    public double calculateEnergy() {
        int height = Integer.parseInt(inputHeight.getText().toString());
        int weight = Integer.parseInt(inputWeight.getText().toString());
        int age = Integer.parseInt(inputAge.getText().toString());

        if (selectedGender == "Male"){
            return 66.47 + (13.7 * weight) + (5.0 * height) - (6.76 * age);
        } else {
            return 655.1 + (9.567 * weight) + (1.85 * height) - (4.68 * age);
        }
    }

    public void switchFragment(Fragment frag) {
        Log.v("CalculateBMI", "Inside fragment switch");
        FragmentManager fm = getParentFragmentManager();
        fm.beginTransaction().replace(R.id.nav_host_fragment, frag).commit();
    }


}