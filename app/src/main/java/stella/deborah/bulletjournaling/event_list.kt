package stella.deborah.bulletjournaling
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import stella.deborah.bulletjournaling.adapter.ItemAdapter
import stella.deborah.bulletjournaling.databinding.FragmentEventListBinding
import stella.deborah.bulletjournaling.model.Event
import stella.deborah.bulletjournaling.ui.kalender.KalenderViewModel


class event_list : Fragment() {
   lateinit var _binding: FragmentEventListBinding
   val arrayList = ArrayList<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEventListBinding.inflate(inflater, container, false)

        return inflater.inflate(R.layout.fragment_event_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val eventViewModel =  ViewModelProvider(this).get(KalenderViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycle_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        eventViewModel.loadEvent()
        eventViewModel.eventLiveData.observe(this.viewLifecycleOwner) {
            Handler(Looper.getMainLooper()).post {
                arrayList.addAll(it)
                val adapter = ItemAdapter(requireContext(), arrayList)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

        }
    }


    }
