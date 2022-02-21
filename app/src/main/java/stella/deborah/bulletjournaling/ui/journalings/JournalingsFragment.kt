package stella.deborah.bulletjournaling.ui.journalings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import stella.deborah.bulletjournaling.R
import stella.deborah.bulletjournaling.databinding.FragmentJournalingsBinding
import stella.deborah.bulletjournaling.databinding.FragmentKalenderBinding
import stella.deborah.bulletjournaling.ui.kalender.KalenderViewModel

class JournalingsFragment : Fragment() {

    private lateinit var journalingsViewModel: JournalingsViewModel
    private var _binding: FragmentJournalingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        journalingsViewModel = ViewModelProvider(this).get(JournalingsViewModel::class.java)

        _binding = FragmentJournalingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.username
       journalingsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}