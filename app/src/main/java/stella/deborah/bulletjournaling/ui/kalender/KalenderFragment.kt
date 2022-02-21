package stella.deborah.bulletjournaling.ui.kalender

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import stella.deborah.bulletjournaling.R
import stella.deborah.bulletjournaling.databinding.FragmentKalenderBinding

class KalenderFragment : Fragment() {

    private lateinit var kalenderViewModel: KalenderViewModel
    private var _binding: FragmentKalenderBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        kalenderViewModel = ViewModelProvider(this).get(KalenderViewModel::class.java)
        _binding = FragmentKalenderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.myevent
        kalenderViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}