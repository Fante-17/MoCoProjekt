package stella.deborah.bulletjournaling.ui.einstellung

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import stella.deborah.bulletjournaling.R

class EinstellungenFragment : Fragment() {

    companion object {
        fun newInstance() = EinstellungenFragment()
    }

    private lateinit var viewModel: EinstellungenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.einstellungen_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EinstellungenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}