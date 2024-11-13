package ro.pub.cs.systems.eim.practicaltest01var05

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var05SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var05_secondary)

        val text_view = findViewById<TextView>(R.id.secondary_activity_text_view)
        val verify_button = findViewById<Button>(R.id.verify_button)
        val cancel_button = findViewById<Button>(R.id.cancel_button)

        text_view.text = intent.getStringExtra("text_view")

        verify_button.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

        cancel_button.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}