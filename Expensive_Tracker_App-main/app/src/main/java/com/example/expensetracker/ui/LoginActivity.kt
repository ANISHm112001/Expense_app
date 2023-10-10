package com.example.expensetracker.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentEditTransactionBinding
import com.example.expensetracker.model.TransactionModel
import com.example.expensetracker.util.Constants
import com.example.expensetracker.util.TransactionCategory
import com.example.expensetracker.util.getCurrencySymbol
import com.example.expensetracker.viewmodel.TransactionsViewModel
import com.google.android.material.snackbar.Snackbar
import java.lang.Double.parseDouble
import java.text.SimpleDateFormat
import java.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize the Room database
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "user-db").build()
        userDao = db.userDao()

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Check if the user exists in the database
            GlobalScope.launch(Dispatchers.IO) {
                val user = userDao.getUser(username, password)
                if (user != null) {
                    // Successful login, navigate to the main activity
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Failed login, show an error message
                    runOnUiThread {
                        // Handle the logic to display an error message to the user
                    }
                }
            }
        }
    }
}