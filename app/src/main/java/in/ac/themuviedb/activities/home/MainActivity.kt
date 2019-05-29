package `in`.ac.themuviedb.activities.home

import `in`.ac.themuviedb.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpBottomNavigation()

        // Set default movie tab selected
        replaceFragment(MuvieFragment())
    }

    /**
     *  Setup Bottom Navigation Bar
     */
    private fun setUpBottomNavigation() {
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    /**
     * Handle Click event on bottom navigation bar
     */
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_songs -> {
                replaceFragment(MuvieFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_albums -> {
                replaceFragment(TvFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_artists -> {
                replaceFragment(FavoriteFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    /**
     * Replace fragments
     */
    private fun  replaceFragment(fragment : Fragment){
        val fragmentTransection = supportFragmentManager.beginTransaction()
        fragmentTransection.replace(R.id.container, fragment)
        fragmentTransection.commit()
    }

}
