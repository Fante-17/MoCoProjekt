package stella.deborah.bulletjournaling.ui.journalings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JournalingsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Journaling Fragment"
    }
    val text: LiveData<String> = _text

    //speichern

    //aufrufen
    //alle journal
}