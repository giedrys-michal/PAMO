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
    public MutableLiveData<Map<String, Integer>> dataStore = new MutableLiveData<>();
    public Map<String, Integer> dataMap = new HashMap<String, Integer>();

    public CalculateViewModel() {
        dataStore.setValue(dataMap);
    }

    public Map<String, Integer> exportAllData(){
        return dataStore.getValue();
    }

    public void importAllData(Map<String, Integer> newDataMap){
        this.dataStore.setValue(newDataMap);
    }

    public Integer getMapValueByKey(String key){
        return dataStore.getValue().get(key);
    }

    public void addDataToMap(String key, Integer value){
        dataStore.getValue().put(key, value);
    }

    public void clearAllData(){
        this.dataStore.getValue().clear();
    }
}