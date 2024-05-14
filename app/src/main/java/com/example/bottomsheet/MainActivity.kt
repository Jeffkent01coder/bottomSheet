package com.example.bottomsheet

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomsheet.databinding.ActivityMainBinding
import com.example.bottomsheet.databinding.BottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private var isExpanded = false


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.cardView.setOnClickListener {
            toggleCard()
        }

        binding.dropdownButton.setOnClickListener {
            toggleCard()
        }

        // Initialize ActionBarDrawerToggle with ViewBinding
        actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.myDrawerLayout, R.string.nav_open, R.string.nav_close)

        // Set the listener to toggle the drawer layout
        binding.myDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // Display the navigation drawer icon on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Access views using binding object
        binding.idBtnShowBottomSheet.setOnClickListener {
            // Create a new bottom sheet dialog
            val dialog = BottomSheetDialog(this)

            // Inflate the layout using ViewBinding
            val bottomSheetBinding = BottomSheetDialogBinding.inflate(layoutInflater)

            // Set up the dialog's content view
            dialog.setContentView(bottomSheetBinding.root)

            // Set click listener for the dismiss button
            bottomSheetBinding.idBtnDismiss.setOnClickListener {
                dialog.dismiss()
            }

            // Set cancelable to avoid closing of dialog box when clicking on the screen
            dialog.setCancelable(false)

            // Show the dialog
            dialog.show()
        }


    }

    private fun toggleCard() {
        if (isExpanded) {
            binding.additionalText.visibility = View.GONE
            binding.mainText.layoutParams.height = resources.getDimensionPixelSize(R.dimen.card_height)
            isExpanded = false
        } else {
            binding.additionalText.visibility = View.VISIBLE
            binding.mainText.layoutParams.height = resources.getDimensionPixelSize(R.dimen.card_expanded_height)
            isExpanded = true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection to open/close the navigation drawer
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}