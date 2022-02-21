package stella.deborah.bulletjournaling.ui.kalender

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KalenderViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "My Event"
    }
    val text: LiveData<String> = _text
}