package stella.deborah.bulletjournaling

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import stella.deborah.bulletjournaling.databinding.ActivityMainBinding
import stella.deborah.bulletjournaling.databinding.FragmentKalenderBinding
import java.util.*

class MainActivity : AppCompatActivity(){

    private lateinit var binding:   ActivityMainBinding
    private lateinit var bindingk: FragmentKalenderBinding
    private lateinit var beginn_date : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingk = FragmentKalenderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        beginn_date = bindingk.begin


        val navController = findNavController(R.id.nav_host_fragment_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    fun showTimePickerDialog(v: View) {
        TimePickerFragment().show(supportFragmentManager, "timePicker")
    }
}