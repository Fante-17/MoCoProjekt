package stella.deborah.bulletjournaling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import stella.deborah.bulletjournaling.databinding.FragmentLoginBinding
import stella.deborah.bulletjournaling.utils.FirebaseUtil


class LoginFragment : Fragment() {
    private var _binding:FragmentLoginBinding? = null
     private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

     _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        //username to be change to email
        binding.login.setOnClickListener {
            login(email = binding.username.text.toString(), password = binding.password.text.toString())
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun login(email:String, password:String){

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5){

            // Normally here we need to show a progress dialog
            FirebaseUtil.firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context,"Login successful",Toast.LENGTH_LONG).show()
                    }
                    else{
                        try{
                            throw task.exception!!
                        }
                        catch (firebaseEx:FirebaseAuthException) {
                            Toast.makeText(context,firebaseEx.message, Toast.LENGTH_LONG).show()
                            return@addOnCompleteListener
                        } catch (ex:Exception){
                            Toast.makeText(context, ex.message, Toast.LENGTH_LONG).show()
                            return@addOnCompleteListener
                        }
                    }

                }
        }
        else{
            Toast.makeText(context, "Email must be correct and password lenght must be greater than 5",Toast.LENGTH_LONG).show()
            return
        }


    }

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseUtil.firebaseAuth.currentUser
        if(currentUser != null){
            //navigate to appropriete destination
            Toast.makeText(context,"A user is already logged in",Toast.LENGTH_LONG).show()
        }
    }
}
