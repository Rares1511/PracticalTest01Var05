package ro.pub.cs.systems.eim.practicaltest01var05

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log

class PracticalTest01Var05Service : Service() {
    private val handler = Handler()
    private lateinit var broadcastIntent: Intent
    private val interval: Long = 5000  // 5 seconds

    // Runnable task that sends broadcast every 5 seconds
    private val runnable = object : Runnable {
        override fun run() {
            Log.d("MyService", "Broadcasting message")
            sendBroadcast(broadcastIntent)
            handler.postDelayed(this, interval)  // Schedule again
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handler.post(runnable)  // Start the periodic broadcast
        broadcastIntent = Intent()
        broadcastIntent.action = "ro.pub.cs.systems.eim.practicaltest01var05.broadcast"
        broadcastIntent.putExtra("message", intent?.getStringExtra("text_view"))
        return START_STICKY  // Keep running until explicitly stopped
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)  // Stop the periodic task
    }
}