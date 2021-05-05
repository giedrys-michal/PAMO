package pamo.bmicalc.ui.calculate;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CalculateViewModel extends ViewModel {
    public MutableLiveData<Map<String, String>> stringDataStore = new MutableLiveData<>();
    public Map<String, String> stringDataMap = new HashMap<>();

    public CalculateViewModel() {
        stringDataStore.setValue(stringDataMap);
    }

    public String getStringMapValueByKey(String key){
        return Objects.requireNonNull(stringDataStore.getValue()).get(key);
    }

    public void addStringDataToMap(String key, String value){
        Objects.requireNonNull(stringDataStore.getValue()).put(key, value);
    }
}