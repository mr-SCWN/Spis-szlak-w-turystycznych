package edu.put.mobapp_lab

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.ShareActionProvider
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    private val shareActionProvider: ShareActionProvider? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pagerAdapter: SectionsPagerAdapter = SectionsPagerAdapter(this)
        val pager = findViewById<View>(R.id.pager) as ViewPager2
        pager.setAdapter(pagerAdapter)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(
            tabLayout, pager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.setText(
                pagerAdapter.getPageTitle(position)
            )
        }.attach()
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.trail_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.author_action -> {
                val intent = Intent(this, Info_Autor::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_switch_theme -> {
                // Code to toggle the theme
                toggleTheme()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun toggleTheme() {
        // Implementation to switch the theme
        // You can use SharedPreferences to save the theme state
        // and use AppCompatDelegate.setDefaultNightMode() to apply the theme
        val nightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (nightMode) {
            Configuration.UI_MODE_NIGHT_YES -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Configuration.UI_MODE_NIGHT_NO -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            Configuration.UI_MODE_NIGHT_UNDEFINED -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
        // This will recreate the Activity to apply the theme immediately
        recreate()
    }


    private inner class SectionsPagerAdapter(fragmentActivity: FragmentActivity?) :
        FragmentStateAdapter(fragmentActivity!!) {
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> Fragment_Gora()
            1 -> `1_Sciezka`()
            2 -> `2_sciezka`()
            else -> throw IllegalStateException("Invalid position: $position") // Handle unexpected case
        }

        // Tytuły stron
        fun getPageTitle(position: Int): CharSequence? = when (position) {
            0 -> resources.getText(R.string.home_tab)
            1 -> resources.getText(R.string.category1_tab)
            2 -> resources.getText(R.string.category2_tab)
            else -> null
        }
    }
}