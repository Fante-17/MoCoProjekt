package stella.deborah.bulletjournaling.utils

import android.annotation.SuppressLint
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FirebaseUtil{
 companion object{
  val firebaseAuth = FirebaseAuth.getInstance()
  @SuppressLint("StaticFieldLeak")
  val firestoreDb = Firebase.firestore

  val usersCollection = firestoreDb.collection("users")
 }


}