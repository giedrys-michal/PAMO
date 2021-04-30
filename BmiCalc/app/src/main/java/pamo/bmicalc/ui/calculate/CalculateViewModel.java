package pamo.bmicalc.ui.calculate;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.Map;

public class CalculateViewModel extends ViewModel {
    public MutableLiveData<Map<String, Double>> doubleDataStore = new MutableLiveData<>();
    public Map<String, Double> doubleDataMap = new HashMap<>();

    public MutableLiveData<Map<String, String>> stringDataStore = new MutableLiveData<>();
    public Map<String, String> stringDataMap = new HashMap<>();

    public CalculateViewModel() {
        doubleDataStore.setValue(doubleDataMap);
        stringDataStore.setValue(stringDataMap);
    }

    public Map<String, Double> exportAllDoubleData(){
        return doubleDataStore.getValue();
    }
    public Map<String, String> exportAllStringData(){
        return stringDataStore.getValue();
    }

    public void importAllDoubleData(Map<String, Double> newDataMap) {
        this.doubleDataStore.setValue(newDataMap);
    }
    public void importAllStringData(Map<String, String> newDataMap) {
        this.stringDataStore.setValue(newDataMap);
    }

    public Double getDoubleMapValueByKey(String key){
        return doubleDataStore.getValue().get(key);
    }
    public String getStringMapValueByKey(String key){
        return stringDataStore.getValue().get(key);
    }

    public void addDoubleDataToMap(String key, Double value){
        doubleDataStore.getValue().put(key, value);
    }
    public void addStringDataToMap(String key, String value){
        stringDataStore.getValue().put(key, value);
    }

    public void clearAllDoubleData(){
        this.doubleDataStore.getValue().clear();
    }
    public void clearAllStringData(){
        this.stringDataStore.getValue().clear();
    }
}