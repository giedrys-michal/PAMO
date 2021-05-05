package pamo.bmicalc_kotlin.ui.calculate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.HashMap

class CalculateViewModel : ViewModel() {
    private var stringDataStore = MutableLiveData<MutableMap<String, String>>()
    private var stringDataMap: MutableMap<String, String> = HashMap()

    init {
        stringDataStore.value = stringDataMap
    }

    fun getStringMapValueByKey(key: String): String? {
        return stringDataStore.value!![key]
    }

    fun addStringDataToMap(key: String, value: String) {
        stringDataStore.value!![key] = value
    }
}