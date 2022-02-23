package stella.deborah.bulletjournaling.ui.registration

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserProfileChangeRequest
import stella.deborah.bulletjournaling.R
import stella.deborah.bulletjournaling.databinding.FragmentRegistrationBinding
import stella.deborah.bulletjournaling.utils.FirebaseUtil

class RegistrationsFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val view = binding.root
        //username to be change to email

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun register(username:String, email:String, password:String, geburstag:String){
        if (username.isBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >5 && geburstag.isBlank()){
            FirebaseUtil.firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task->
                    if (task.isSuccessful){
                        val currentUser = task.result?.user
                        val profileUpdates: UserProfileChangeRequest =  UserProfileChangeRequest.Builder()
                            .setDisplayName(username)
                            .build()

                        currentUser?.updateProfile(profileUpdates)
                            ?.addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Toast.makeText(context,"User name set", Toast.LENGTH_LONG).show()
                                } else {
                                    val excption = it.exception
                                    try {

                                    }catch (ex: FirebaseAuthException){

                                    } catch (ex:Exception) {
                                        Toast.makeText(context,"${ex.message}", Toast.LENGTH_LONG)
                                            .show()
                                        return@addOnCompleteListener
                                    }
                                }
                            }

                    }
                }
        }




    }

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseUtil.firebaseAuth.currentUser
        if(currentUser != null){
            //navigate to appropriete destination
            Toast.makeText(context,"A user is already logged in", Toast.LENGTH_LONG).show()
        }
    }


}