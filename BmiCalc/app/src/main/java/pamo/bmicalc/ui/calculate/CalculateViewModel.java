package pamo.bmicalc.ui.calculate;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculateViewModel extends ViewModel {
    public MutableLiveData<Integer> bmiResult = new MutableLiveData<>();

    public Integer exportResult(){
        return bmiResult.getValue();
    }

    public void importResult(Integer result){
        this.bmiResult.setValue(result);
    }
}