package pamo.bmicalc.ui.calculate;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pamo.bmicalc.R;

public class BmiResultsFragment extends Fragment {

    private CalculateViewModel cViewModel;
    private TextView tv_bmiResult, tv_energyResult, tv_category;

    public static BmiResultsFragment newInstance() {
        return new BmiResultsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmi_results, container, false);
        cViewModel = new ViewModelProvider(getActivity()).get(CalculateViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        tv_bmiResult = (TextView) view.findViewById(R.id.bmiResults_bmi_text);
        tv_energyResult = (TextView) view.findViewById(R.id.bmiResults_energy_text);
        tv_category = (TextView) view.findViewById(R.id.bmiResults_category_text);
        updateResults(cViewModel.getDoubleMapValueByKey("bmiResult"), cViewModel.getDoubleMapValueByKey("energyResult"), cViewModel.getStringMapValueByKey("bmiCategory"));
    }

    @SuppressLint("SetTextI18n")
    public void updateResults(double bmiValue, double energyValue, String bmiCategory){
        tv_bmiResult.setText(Double.toString(bmiValue));
        tv_category.setText(bmiCategory);
        tv_energyResult.setText(Double.toString(energyValue));
    }

}