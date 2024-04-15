package edu.put.mobapp_lab

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class Oblicznaie_Czasu : Fragment(), View.OnClickListener {
    private var seconds = 0
    private var running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout: View = inflater.inflate(R.layout.obliczanie_czasu_xml, container, false)
        runTimer(layout)
        layout.findViewById<Button>(R.id.start_button).setOnClickListener(this)
        layout.findViewById<Button>(R.id.stop_button).setOnClickListener(this)
        layout.findViewById<Button>(R.id.reset_button).setOnClickListener(this)
        layout.findViewById<Button>(R.id.save_time_button).setOnClickListener(this) // Dodanie nowego przycisku
        return layout
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.start_button -> onClickStart()
            R.id.stop_button -> onClickStop()
            R.id.reset_button -> onClickReset()
            R.id.save_time_button -> onClickSaveTime()
        }
    }

    private fun onClickSaveTime() {
        val sharedPreferences = activity?.getSharedPreferences("StoperPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putInt("savedTime", seconds)
        editor?.apply()

        // Aktualizacja TextView z zapisanym czasem
        updateSavedTimeView()
    }

    private fun updateSavedTimeView() {
        val savedTime = readSavedTime()
        val savedTimeView = view?.findViewById<TextView>(R.id.saved_time_view)
        savedTimeView?.text = formatTime(savedTime)
    }

    private fun readSavedTime(): Int {
        val sharedPreferences = activity?.getSharedPreferences("StoperPrefs", Context.MODE_PRIVATE)
        return sharedPreferences?.getInt("savedTime", 0) ?: 0
    }

    private fun formatTime(seconds: Int): String {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val secs = seconds % 60
        return String.format("Zapisany czas: %d:%02d:%02d", hours, minutes, secs)
    }

    private fun onClickStart() {
        running = true
    }

    private fun onClickStop() {
        running = false
    }

    private fun onClickReset() {
        running = false
        seconds = 0
    }

    private fun runTimer(view: View) {
        val timeView = view.findViewById<TextView>(R.id.time_view)
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = seconds % 3600 / 60
                val secs = seconds % 60
                val time = String.format("%d:%02d:%02d", hours, minutes, secs)
                timeView.text = time
                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("seconds", seconds)
        savedInstanceState.putBoolean("running", running)
    }
}
