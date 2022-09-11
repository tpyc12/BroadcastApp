package com.myhome.android.broadcastapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        when(intent?.action){
            ACTION_CLICKED -> {
                val count = intent.getIntExtra("Count", 0)
                Toast.makeText(context, "Click $count", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                Toast.makeText(context, "Airplane mode changed", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_BATTERY_LOW -> {
                val turnedOn = intent.getBooleanExtra("state", false)
                Toast.makeText(context, "Battery low $turnedOn", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object{

        const val ACTION_CLICKED = "clicked"
    }
}