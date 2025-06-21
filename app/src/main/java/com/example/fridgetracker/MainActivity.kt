package com.example.fridgetracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import com.example.fridgetracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var contentsViewModel: ContentsViewModel
    private lateinit var fridgeContents: ArrayList<String>
    private lateinit var fridgeContentsAdapter: ArrayAdapter<String>
    private lateinit var itemList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contentsViewModel = ViewModelProvider(this)[ContentsViewModel::class.java]

        setSupportActionBar(binding.toolbar)

        itemList = findViewById<ListView>(R.id.list)

        fridgeContents = ArrayList<String>()
        fridgeContentsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fridgeContents)
        itemList.setAdapter(fridgeContentsAdapter)
        fridgeContentsAdapter.add("Item 1")  // Replace with item added via dialogue

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            NewItemSheet().show(supportFragmentManager, "newContentTag")
        }

        contentsViewModel.name.observe(this){
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}