package pamo.bmicalc.ui.calculate;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pamo.bmicalc.R;

public class BmiResultsFragment extends Fragment {

    private CalculateViewModel cViewModel;
    private TextView text_bmiResult, text_energyResult;

    public static BmiResultsFragment newInstance() {
        return new BmiResultsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmi_results, container, false);
        Log.v("BMI_RESULTS", "BMI_results fragment create fired");
        cViewModel = new ViewModelProvider(getActivity()).get(CalculateViewModel.class);
        Log.v("Results_TAG", "BMI_Result: " + cViewModel.exportAllData());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Log.v("BMI_RESULTS", "BMI_results fragment created SUCCESSFULLY");

        text_bmiResult = (TextView) view.findViewById(R.id.bmiResults_bmi_text);
        text_energyResult = (TextView) view.findViewById(R.id.bmiResults_energy_text);
        updateResults(cViewModel.getMapValueByKey("bmiResult"), cViewModel.getMapValueByKey("energyResult"));
    }

    @SuppressLint("SetTextI18n")
    public void updateResults(double bmiValue, double energyValue){
        text_bmiResult.setText(Double.toString(bmiValue));
        text_energyResult.setText(Double.toString(energyValue) + " kcal");
    }

}