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
    private TextView tv_bmiResult, tv_energyResult, tv_category, tv_recipe;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmi_results, container, false);
        cViewModel = new ViewModelProvider(requireActivity()).get(CalculateViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        tv_bmiResult = view.findViewById(R.id.bmiResults_bmi_text);
        tv_energyResult = view.findViewById(R.id.bmiResults_energy_text);
        tv_category = view.findViewById(R.id.bmiResults_category_text);
        tv_recipe = view.findViewById(R.id.bmiResults_recipe_text);

        updateResults(
                cViewModel.getStringMapValueByKey("bmiResult"),
                cViewModel.getStringMapValueByKey("energyResult"),
                cViewModel.getStringMapValueByKey("bmiCategory"),
                cViewModel.getStringMapValueByKey("bmiRecipe")
        );
    }

    @SuppressLint("SetTextI18n")
    public void updateResults(String bmiValue, String energyValue, String bmiCategory, String bmiRecipe) {
        tv_bmiResult.setText(bmiValue);
        tv_category.setText(bmiCategory);
        tv_energyResult.setText(energyValue);
        tv_recipe.setText(bmiRecipe);
    }
}