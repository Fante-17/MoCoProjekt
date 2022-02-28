package stella.deborah.bulletjournaling

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import stella.deborah.bulletjournaling.databinding.ActivityMainBinding
import stella.deborah.bulletjournaling.databinding.FragmentJournalingsBinding
import stella.deborah.bulletjournaling.ui.journalings.JournalingsFragment
import stella.deborah.bulletjournaling.ui.login.LoginsFragment
import stella.deborah.bulletjournaling.ui.registration.RegistrationsFragment

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var frameLayout: FrameLayout
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
         frameLayout = binding.frameContainer
        drawerLayout = binding.drawerLayout


        val navController = findNavController(R.id.nav_host_fragment_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_journalings, R.id.navigation_kalender,R.id.navigation_registration,R.id.navigation_einstellungen, R.id.loginsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navViewMenu.setNavigationItemSelectedListener {
            it.isChecked =true
            when(it.itemId){
                R.id.navigation_login-> navController.navigate(R.id.loginsFragment)
                R.id.navigation_register-> navController.navigate(R.id.navigation_registration)
                R.id.navigation_journal-> navController.navigate(R.id.navigation_journalings)
                R.id.navigation_event-> navController.navigate(R.id.event_list)
            }
            true
        }
    }

private  fun replace(fragment: Fragment){
    val fragmentManager = supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.replace(frameLayout.id, fragment)
    fragmentTransaction.commit()
   drawerLayout.closeDrawers()
}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return  super.onOptionsItemSelected(item)
    }
}