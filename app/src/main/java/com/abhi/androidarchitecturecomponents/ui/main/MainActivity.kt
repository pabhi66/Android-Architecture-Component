package com.abhi.androidarchitecturecomponents.ui.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.abhi.androidarchitecturecomponents.R
import com.abhi.androidarchitecturecomponents.databinding.ActivityMainBinding
import com.abhi.androidarchitecturecomponents.ui.base.BaseActivity

import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 1/2/18.
 *
 * This is a main activity that is launched when the app starts
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    /**
     * get activity layout
     */
    override fun getLayoutRes(): Int = R.layout.activity_main

    /**
     * override on create and set toolbar
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
    }

    /**
     * override options menu
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    /**
     * override options menu click
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
