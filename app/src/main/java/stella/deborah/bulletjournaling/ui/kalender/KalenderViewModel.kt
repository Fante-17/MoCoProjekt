package stella.deborah.bulletjournaling.ui.kalender

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import stella.deborah.bulletjournaling.MainViewModel
import stella.deborah.bulletjournaling.model.Event

class KalenderViewModel : ViewModel() {

    companion object {
        private val TAG = KalenderViewModel::class.java.name
    }
    val eventLiveData = MutableLiveData<List<Event>>()
    val eventCreatedLiveData = MutableLiveData<Boolean>()


    private val db = Firebase.firestore

    private val _text = MutableLiveData<String>().apply {
        value = "My Event"
    }
    val text: LiveData<String> = _text



    fun createEvent(event: Event) {
        db.collection("events")
            .add(event)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                eventCreatedLiveData.value = true
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
                eventCreatedLiveData.value = false
            }
    }



    fun loadEvent() {
        db.collection("events")
            .get()
            .addOnSuccessListener { result ->
                val data = result.toObjects(Event::class.java)
                eventLiveData.value = data.toList()
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

}