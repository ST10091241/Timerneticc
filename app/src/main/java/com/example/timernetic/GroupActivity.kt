package com.example.timernetic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.timernetic.utils.groupData
import com.example.timernetic.utils.taskData
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.*

class GroupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var dataReference: DatabaseReference
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle
    //page components
    private lateinit var closeGroupPopUpBtn:ImageView
    private lateinit var taskGroupName:EditText
    private lateinit var addGroupbtn:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        dataReference = FirebaseDatabase.getInstance().reference
            .child("Category").child(auth.currentUser?.uid.toString())
        drawerLayout = findViewById(R.id.drawer_layout)
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer)

        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener { item ->
            // Handle navigation item clicks here
            when (item.itemId) {
                R.id.nav_option1 -> {
                    // option 1 click
                    // val navController = findNavController(this, R.id.nav_host_fragment)
                    // navController.navigate(R.id.action_splashFragment_to_homeFragment)
                }
                R.id.nav_option2 -> {
                    val intent = Intent(this, GoalsActivity::class.java)
                    startActivity(intent)
                }


                R.id.nav_option3 -> {
                    // Handle option 3 click
                    val intent = Intent(this, ViewTimesheetActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_option4 -> {
                    // Handle option 4 click

                }
                R.id.nav_option5 -> {
                    // Handle option 5 click
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        setContentView(R.layout.activity_group)
        //set page components
        closeGroupPopUpBtn = findViewById(R.id.closeGroupPopUpBtn)
        closeGroupPopUpBtn.setOnClickListener{
            val intent = Intent(this@GroupActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        addGroupbtn = findViewById(R.id.addGroupbtn)
        taskGroupName = findViewById(R.id.taskGroupName)
        addGroupbtn.setOnClickListener{
            val group = groupData("Category",taskGroupName.text.toString())
            dataReference.child(group.taskId).push().setValue(group).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Group created successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    taskGroupName.text = null
                } else {
                    Toast.makeText(applicationContext, it.exception?.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}