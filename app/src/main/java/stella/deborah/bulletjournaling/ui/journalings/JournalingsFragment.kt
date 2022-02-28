package stella.deborah.bulletjournaling.ui.journalings

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import stella.deborah.bulletjournaling.databinding.FragmentJournalingsBinding

class JournalingsFragment : Fragment() {

    private lateinit var journalingsViewModel: JournalingsViewModel
    private var _binding: FragmentJournalingsBinding? = null

    private val selectPictureGallery = registerForActivityResult(ActivityResultContracts.GetContent()){
        imageView.setImageURI(it)
    }
    private var tempImageUri: Uri? =null
    private val selectPictureCamara = registerForActivityResult(ActivityResultContracts.TakePicture()){
        sucess->
        if (sucess)
            imageView.setImageURI(tempImageUri)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        journalingsViewModel = ViewModelProvider(this).get(JournalingsViewModel::class.java)
        _binding = FragmentJournalingsBinding.inflate(inflater,container,false)
        imageView = binding.imageview
        gallery = binding.gallery
        gallery.setOnClickListener(
            {selectPictureGallery.launch("image/*")}
        )
      /*imageView.setOnClickListener({

            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "images/*"
            startActivity(intent)
        } )

       */
       */

        val root: View = binding.root
        val textView: TextView = binding.username
       journalingsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }




    lateinit var imageView: ImageView
    lateinit var gallery:Button

    private fun onImageViewClicked(v:View){
        val intent:Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivity(intent)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView.setOnClickListener(this::onImageViewClicked)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //aufrufen
   // journallist.set
}