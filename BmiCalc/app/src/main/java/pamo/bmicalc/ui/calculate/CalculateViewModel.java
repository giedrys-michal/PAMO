package pamo.bmicalc.ui.calculate;

import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class CalculateViewModel extends ViewModel {
    private final MutableLiveData<Integer> bmiResult;

    public CalculateViewModel(SavedStateHandle state){
        bmiResult = state.getLiveData("Default Message");
        // PAMO_CALC bookmark on PC - continue there
    }
}