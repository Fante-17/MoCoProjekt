package stella.deborah.bulletjournaling.ui.kalender

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import stella.deborah.bulletjournaling.DatePickerFragment
import stella.deborah.bulletjournaling.R
import stella.deborah.bulletjournaling.TimePickerFragment
import stella.deborah.bulletjournaling.databinding.FragmentKalenderBinding
import stella.deborah.bulletjournaling.model.PickerDate
import stella.deborah.bulletjournaling.model.Time

class KalenderFragment : Fragment() {

    companion object {
        const val TAG = "KalenderFragment"
        fun newInstance() = KalenderFragment()
    }

    private lateinit var kalenderViewModel: KalenderViewModel
    private var _binding: FragmentKalenderBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKalenderBinding.inflate(inflater,container,false)
        startDay = binding.startDay
         startTime= binding.startTime
        endDay= binding.endDay
        endTime= binding.endTime

        val root: View = binding.root

       /* val textView: TextView = binding.myevent
        kalenderViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }
    private var pickedDate: PickerDate? = null
    private var timed: Time? = null

    lateinit  var startDay: TextView
    lateinit var endDay: TextView
    lateinit var startTime:TextView
    lateinit var endTime: TextView

    private fun showDatePickerDialogBegin(v: View) {
        val newFragment = DatePickerFragment { date ->
            pickedDate = date
            startDay.text = "${date.day}.${date.month + 1}.${date.year}"
            Log.w(TAG, "User picked a date: $pickedDate")
        }
        newFragment.show(childFragmentManager, "datePicker")
    }
    private fun showDatePickerDialogEnd(v: View) {
        val newFragment = DatePickerFragment { date ->
            pickedDate = date
            endDay.text = "${date.day}.${date.month + 1}.${date.year}"
            Log.w(TAG, "User picked a date: $pickedDate")
        }
        newFragment.show(childFragmentManager, "datePicker")
    }

    fun showTimePickerDialogBegin(v: View) {
     val newFragment =   TimePickerFragment{ time->
            timed = time
         startTime.text ="${time.hour}:${time.mins}"
        }
         newFragment.show(childFragmentManager, "timePicker")
    }
    fun showTimePickerDialogEnd(v: View) {
        val newFragment =   TimePickerFragment{ time->
            timed = time
            endTime.text ="${time.hour}:${time.mins}"
        }
        newFragment.show(childFragmentManager, "timePicker")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startDay.setOnClickListener(this::showDatePickerDialogBegin)
        startTime.setOnClickListener(this::showTimePickerDialogBegin)
        endDay.setOnClickListener(this::showDatePickerDialogEnd)
        endTime.setOnClickListener(this::showTimePickerDialogEnd)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}