package stella.deborah.bulletjournaling.ui.kalender

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import stella.deborah.bulletjournaling.DatePickerFragment
import stella.deborah.bulletjournaling.TimePickerFragment
import stella.deborah.bulletjournaling.databinding.FragmentKalenderBinding
import stella.deborah.bulletjournaling.model.Event
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
        kalenderViewModel = ViewModelProvider(this).get(KalenderViewModel::class.java)

        startDay = binding.startDay
        startTime = binding.startTime
        endDay = binding.endDay
        endTime = binding.endTime
        eventName = binding.nameEvent
        eventPlace = binding.startort
        eventType = binding.artNotiz
        saveButton = binding.saveButton
        deleteButton = binding.deleteButton

        val root: View = binding.root

       /* val textView: TextView = binding.myevent
        kalenderViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }
    private var pickedDate: PickerDate? = null
    private var timed: Time? = null

    lateinit var startDay: TextView
    lateinit var endDay: TextView
    lateinit var startTime:TextView
    lateinit var endTime: TextView

    lateinit var eventName :TextView
    lateinit var eventPlace : TextView
    lateinit var eventType : TextView

    lateinit var saveButton : Button
    lateinit var deleteButton : Button

    private fun showDatePickerDialogBegin(v: View) {
        val newFragment = DatePickerFragment { date ->
            pickedDate = date
            "${date.day}.${date.month + 1}.${date.year}".also { startDay.text = it }
            Log.w(TAG, "User picked a date: $pickedDate")
        }
        newFragment.show(childFragmentManager, "datePicker")
    }
    private fun showDatePickerDialogEnd(v: View) {
        val newFragment = DatePickerFragment { date ->
            pickedDate = date
            "${date.day}.${date.month + 1}.${date.year}".also { endDay.text = it }
            Log.w(TAG, "User picked a date: $pickedDate")
        }
        newFragment.show(childFragmentManager, "datePicker")
    }

    private fun showTimePickerDialogBegin(v: View) {
     val newFragment =   TimePickerFragment{ time->
            timed = time
         "${time.hour}:${time.mins}".also { startTime.text = it }
        }
         newFragment.show(childFragmentManager, "timePicker")
    }
    private fun showTimePickerDialogEnd(v: View) {
        val newFragment =   TimePickerFragment{ time->
            timed = time
            "${time.hour}:${time.mins}".also { endTime.text = it }
        }
        newFragment.show(childFragmentManager, "timePicker")
    }

    private fun onSaveButtonClicked(view: View){
        val beginDay = startDay.text.toString()
        val terminateDay = endDay.text.toString()
        val beginTime = startTime.text.toString()
        val terminateTime = endTime.text.toString()
        val nameOfEvent = eventName.text.toString()
        val placeOfEvent = eventPlace.text.toString()
        val typeOfEvent = eventType.text.toString()
        kalenderViewModel.createEvent(Event(nameOfEvent,placeOfEvent,typeOfEvent,beginDay,beginTime,terminateDay,terminateTime) )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startDay.setOnClickListener(this::showDatePickerDialogBegin)
        startTime.setOnClickListener(this::showTimePickerDialogBegin)
        endDay.setOnClickListener(this::showDatePickerDialogEnd)
        endTime.setOnClickListener(this::showTimePickerDialogEnd)
        saveButton.setOnClickListener(this::onSaveButtonClicked)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}