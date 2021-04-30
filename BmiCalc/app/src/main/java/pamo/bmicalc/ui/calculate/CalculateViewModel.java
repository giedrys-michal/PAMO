package pamo.bmicalc.ui.calculate;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

public class CalculateViewModel extends ViewModel {
    public MutableLiveData<Map<String, String>> stringDataStore = new MutableLiveData<>();
    public Map<String, String> stringDataMap = new HashMap<>();

    public CalculateViewModel() {
        stringDataStore.setValue(stringDataMap);
    }

    public Map<String, String> exportAllStringData(){
        return stringDataStore.getValue();
    }

    public void importAllStringData(Map<String, String> newDataMap) {
        this.stringDataStore.setValue(newDataMap);
    }

    public String getStringMapValueByKey(String key){
        return stringDataStore.getValue().get(key);
    }

    public void addStringDataToMap(String key, String value){
        stringDataStore.getValue().put(key, value);
    }

    public void clearAllStringData(){
        this.stringDataStore.getValue().clear();
    }
}