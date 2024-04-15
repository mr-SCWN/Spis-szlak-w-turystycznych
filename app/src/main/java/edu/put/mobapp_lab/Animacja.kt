package edu.put.mobapp_lab

import android.content.Intent
import android.os.Handler
import androidx.fragment.app.Fragment


class Animacja : Jeden_Fragment() {
    var handler = Handler()
    override fun createFragment(): Fragment {
        handler.postDelayed(mLaunchTask, 5000)
        return Animacja_Fragment.newInstance()
    }

    private val mLaunchTask = Runnable {
        val intent = Intent(
            applicationContext,
            MainActivity::class.java
        )
        startActivity(intent)
    }
}