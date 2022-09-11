package com.myhome.android.broadcastapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar

    private val localBroadcastManager by lazy {
        LocalBroadcastManager.getInstance(this)
    }

    private val receiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "loaded"){
                val percent = intent.getIntExtra("percent", 0)
                progressBar.progress = percent
            }
        }
    }

    private var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar)
        findViewById<TextView>(R.id.tvHello).setOnClickListener {
            Intent(MyReceiver.ACTION_CLICKED).apply {
                putExtra("Count", count++)
                localBroadcastManager.sendBroadcast(this)
            }
        }
        val intentFilter = IntentFilter().apply {
            addAction("loaded")
        }
        localBroadcastManager.registerReceiver(receiver, intentFilter)
        Intent(this, MyService::class.java).apply {
            startService(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        localBroadcastManager.unregisterReceiver(receiver)
    }
}