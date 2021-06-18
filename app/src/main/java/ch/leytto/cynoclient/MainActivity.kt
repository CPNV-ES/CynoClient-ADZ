package ch.leytto.cynoclient

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import ch.leytto.cynoclient.ui.clients.ClientDetailsFragment
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var light_action: MenuItem
    private lateinit var dark_action: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val speedDial = findViewById<SpeedDialView>(R.id.speed_dial)
        speedDial.addActionItem(
            SpeedDialActionItem.Builder(R.id.fab_action_create_client, R.drawable.ic_baseline_person_add)
                .create())
        speedDial.addActionItem(
            SpeedDialActionItem.Builder(R.id.fab_action_create_report, R.drawable.ic_outline_note_add).create()
        )

        speedDial.setOnActionSelectedListener(SpeedDialView.OnActionSelectedListener { actionItem ->
            when (actionItem.id) {
                R.id.fab_action_create_client -> {
                    val intent = Intent(this, CreateClientActivity::class.java)
                    startActivity(intent)

                    return@OnActionSelectedListener true
                }
                R.id.fab_action_create_report -> {
                    val intent = Intent(this, CreateReportActivity::class.java)
                    startActivity(intent)
                    return@OnActionSelectedListener true
                }
            }
            false
        })

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_clients, R.id.nav_dogs, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        toolbar.setNavigationOnClickListener {
            // Handle navigation icon press
            drawerLayout.openDrawer(Gravity.LEFT);
        }

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.light_mode -> {
                    // Handle favorite icon press
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    light_action.isVisible = false
                    dark_action.isVisible = true
                    true
                }
                R.id.dark_mode -> {
                    // Handle search icon press
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    light_action.isVisible = true
                    dark_action.isVisible = false
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        light_action = menu.findItem(R.id.light_mode)
        dark_action = menu.findItem(R.id.dark_mode)

        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {light_action.isVisible = false}
            Configuration.UI_MODE_NIGHT_YES -> {dark_action.isVisible = false}
        }

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}