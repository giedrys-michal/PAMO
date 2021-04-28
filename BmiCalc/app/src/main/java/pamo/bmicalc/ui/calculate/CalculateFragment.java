package pamo.bmicalc.ui.calculate;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import pamo.bmicalc.R;

public class CalculateFragment extends Fragment {

    private CalculateViewModel mViewModel;
    private static EditText inputHeight;
    private static EditText inputWeight;
    private static int calculatedResult;

    public static CalculateFragment newInstance() {
        return new CalculateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate, container, false);
        inputHeight = (EditText) view.findViewById(R.id.calculate_input_height);
        inputWeight = (EditText) view.findViewById(R.id.calculate_input_weight);
        final Button btn_submit = (Button) view.findViewById(R.id.btn_calculateBmi);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBmi();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalculateViewModel.class);
        // TODO: Use the ViewModel
    }

    public void calculateBmi() {
        int height = Integer.parseInt(inputHeight.getText().toString());
        int weight = Integer.parseInt(inputWeight.getText().toString());
        int bmi_i = (int) Math.round(weight / Math.pow(((double) height / 100), 2));

        calculatedResult = bmi_i;
    }


}