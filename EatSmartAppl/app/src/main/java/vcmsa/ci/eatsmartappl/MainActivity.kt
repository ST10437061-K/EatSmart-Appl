package vcmsa.ci.eatsmartappl

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess
//import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Declaring UI Elements

        //val imageAvocado = findViewById<ImageView>(R.id.imageAvocado)
        //val tvHeader = findViewById<TextView>(R.id.tvHeader)
        val etInput = findViewById<EditText>(R.id.etInput)
        val btnSuggestion = findViewById<Button>(R.id.btnSuggestion)
        val tvOutput = findViewById<TextView>(R.id.tvOutput)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val btnExit = findViewById<Button>(R.id.btnExit)

        // Handle system bars insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Meal suggestion logic
        btnSuggestion.setOnClickListener { // Suggestion button functionality

            val timeOfDay = etInput.text.toString().trim().lowercase()

            val mealSuggestion = when (timeOfDay) {
                "morning" -> "Scrambled Eggs & Toast or Oatmeal with Fruits"
                "mid-morning" -> "Granola & Yogurt, or Nuts & Dried Fruits"
                "afternoon" -> "Chicken Wrap or Veggie Stir-Fry"
                "mid-afternoon" -> "Popcorn, Fruit Salad, or an Energy Bar"
                "dinner" -> "Grilled Salmon with Veggies or Chicken Curry & Rice"
                "after dinner snack" -> "Dark Chocolate & Nuts or  Herbal Tea & Crackers"
                else -> null
            }
            // Display meal suggestion or error
            if (mealSuggestion != null) {
                tvOutput.text = "Suggested Meal: $mealSuggestion"
            } else {
                tvOutput.text = ""
                Toast.makeText(this, "Invalid time of day. Please enter a valid option.", Toast.LENGTH_SHORT).show()
            }
        }
        // Reset button functionality
        btnReset.setOnClickListener {
            etInput.text.clear()
            tvOutput.text = ""

        // Exit button functionality
            btnExit.setOnClickListener {
                moveTaskToBack(true)
                android.os.Process.killProcess(android.os.Process.myPid())
                exitProcess(0)
            }

          }
       }
    }
