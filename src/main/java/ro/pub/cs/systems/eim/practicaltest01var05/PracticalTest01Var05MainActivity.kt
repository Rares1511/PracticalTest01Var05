package ro.pub.cs.systems.eim.practicaltest01var05

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var05MainActivity : AppCompatActivity() {

    var total_button_presses = 0
    lateinit var broadcastReceiver: PracticalTest01Var05BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var05_main)

        broadcastReceiver = PracticalTest01Var05BroadcastReceiver()
        val navigate_to_secondary_activity_button = findViewById<Button>(R.id.navigate_to_secondary_activity_button)
        val top_left_button = findViewById<Button>(R.id.top_left_button)
        val top_right_button = findViewById<Button>(R.id.top_right_button)
        val center_button = findViewById<Button>(R.id.center_button)
        val bottom_left_button = findViewById<Button>(R.id.bottom_left_button)
        val bottom_right_button = findViewById<Button>(R.id.bottom_right_button)
        val textView = findViewById<TextView>(R.id.textView)

        top_left_button.setOnClickListener {
            val text = textView.text.toString()
            textView.text = text + " " + top_left_button.text
            total_button_presses++
            if (total_button_presses > 5) {
                Log.d("my", "Text view has more than 20 characters")
                val serviceIntent = Intent(this, PracticalTest01Var05Service::class.java)
                serviceIntent.putExtra("text_view", textView.text.toString())
                startService(serviceIntent)
            }
        }

        top_right_button.setOnClickListener {
            val text = textView.text.toString()
            textView.text = text + " " + top_right_button.text
            total_button_presses++
            if (total_button_presses > 5) {
                Log.d("my", "Text view has more than 20 characters")
                val serviceIntent = Intent(this, PracticalTest01Var05Service::class.java)
                serviceIntent.putExtra("text_view", textView.text.toString())
                startService(serviceIntent)
            }
        }

        center_button.setOnClickListener {
            val text = textView.text.toString()
            textView.text = text + " " + center_button.text
            total_button_presses++
            if (total_button_presses > 5) {
                Log.d("my", "Text view has more than 20 characters")
                val serviceIntent = Intent(this, PracticalTest01Var05Service::class.java)
                serviceIntent.putExtra("text_view", textView.text.toString())
                startService(serviceIntent)
            }
        }

        bottom_left_button.setOnClickListener {
            val text = textView.text.toString()
            textView.text = text + " " + bottom_left_button.text
            total_button_presses++
            if (total_button_presses > 5) {
                Log.d("my", "Text view has more than 20 characters")
                val serviceIntent = Intent(this, PracticalTest01Var05Service::class.java)
                serviceIntent.putExtra("text_view", textView.text.toString())
                startService(serviceIntent)
            }
        }

        bottom_right_button.setOnClickListener {
            val text = textView.text.toString()
            textView.text = text + " " + bottom_right_button.text
            total_button_presses++
            if (total_button_presses > 5) {
                Log.d("my", "Text view has more than 20 characters")
                val serviceIntent = Intent(this, PracticalTest01Var05Service::class.java)
                serviceIntent.putExtra("text_view", textView.text.toString())
                startService(serviceIntent)
            }
        }

        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "The activity returned with OK", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this, "The activity returned with CANCEL", Toast.LENGTH_LONG).show()
            }
        }

        navigate_to_secondary_activity_button.setOnClickListener {
            val intent = Intent(this, PracticalTest01Var05SecondaryActivity::class.java)
            intent.putExtra("text_view", textView.text.toString())
            startForResult.launch(intent)
            textView.text = ""
            total_button_presses = 0
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter("ro.pub.cs.systems.eim.practicaltest01var05.broadcast")
        registerReceiver(broadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(broadcastReceiver)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("total_button_presses", total_button_presses)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey("total_button_presses")) {
            total_button_presses = savedInstanceState.getInt("total_button_presses")
            Toast.makeText(this, "Total button presses: $total_button_presses", Toast.LENGTH_LONG).show()
        }
    }
}