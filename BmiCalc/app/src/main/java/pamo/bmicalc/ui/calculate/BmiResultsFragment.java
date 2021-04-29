package pamo.bmicalc.ui.calculate;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pamo.bmicalc.R;

public class BmiResultsFragment extends Fragment {

    private BmiResultsViewModel mViewModel;
    private CalculateViewModel cViewModel;
    private TextView text_bmiResult;

    public static BmiResultsFragment newInstance() {
        return new BmiResultsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmi_results, container, false);
        Log.v("BMI_RESULTS", "BMI_results fragment create fired");
        cViewModel = new ViewModelProvider(getActivity()).get(CalculateViewModel.class);
        Log.v("Results_TAG", "BMI_Result: " + cViewModel.exportResult());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Log.v("BMI_RESULTS", "BMI_results fragment created SUCCESSFULLY");

        text_bmiResult = (TextView) view.findViewById(R.id.bmiResults_result_text);
        updateBmiResult(cViewModel.exportResult());
    }

    public void updateBmiResult(int bmiValue){
        text_bmiResult.setText(Integer.toString(bmiValue));
    }

}