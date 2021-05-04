package pamo.bmicalc_kotlin.ui.calculate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculateViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is calculate Fragment"
    }
    val text: LiveData<String> = _text
}