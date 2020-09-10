package com.xently.shoppingassistant

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.dynamicfeatures.fragment.DynamicNavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import com.xently.shoppingassistant.databinding.ActivityMainBinding
import com.xently.utilities.ui.SearchableActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : SearchableActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
        (supportFragmentManager.findFragmentById(R.id.nav_host) as? DynamicNavHostFragment)?.apply {
            this@MainActivity.navController = navController
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)

    override fun onSearchIntentReceived(query: String, searchData: Bundle?) = Unit
}