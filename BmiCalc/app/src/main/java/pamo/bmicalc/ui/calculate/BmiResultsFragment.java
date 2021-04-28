package pamo.bmicalc.ui.calculate;

import androidx.lifecycle.ViewModelProvider;

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

    private BmiResultsViewModel mViewModel;
    private CalculateFragment cFrag;
    private static TextView text_bmiResult;

    public static BmiResultsFragment newInstance() {
        return new BmiResultsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bmi_results_fragment, container, false);
        text_bmiResult = (TextView) view.findViewById(R.id.bmiResults_result_text);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BmiResultsViewModel.class);
        // TODO: Use the ViewModel
    }

    public void updateBmiResult(int bmiValue){
        text_bmiResult.setText(Integer.toString(bmiValue));
    }

}