package pamo.bmicalc.ui.calculate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CalculateViewModel extends ViewModel {
    public MutableLiveData<Map<String, Double>> dataStore = new MutableLiveData<>();
    public Map<String, Double> dataMap = new HashMap<>();

    public CalculateViewModel() {
        dataStore.setValue(dataMap);
    }

    public Map<String, Double> exportAllData(){
        return dataStore.getValue();
    }

    public void importAllData(Map<String, Double> newDataMap) {
        this.dataStore.setValue(newDataMap);
    }

    public Double getMapValueByKey(String key){
        return dataStore.getValue().get(key);
    }

    public void addDataToMap(String key, Double value){
        dataStore.getValue().put(key, value);
    }

    public void clearAllData(){
        this.dataStore.getValue().clear();
    }
}