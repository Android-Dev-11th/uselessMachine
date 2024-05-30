package com.example.uselessmachine

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import  android.widget.Button
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group

class MainActivity : AppCompatActivity() {
    //declare vars
    lateinit var selfDestruct: Button
    lateinit var lookBusy: Button
    lateinit var switch1: Switch
    lateinit var mainL: ConstraintLayout
    lateinit var groupBase: Group
    lateinit var groupBusy: Group
    lateinit var text: TextView
    lateinit var bar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wired()

        //setup
        var de = Color.rgb(240, 224, 204)
        mainL.setBackgroundColor(de)
        var c = Color.rgb(255, 225, 225)


        //google listener for switch
        //lambda{} must name 2 params- 1 param auto-names "it"
        switch1.setOnCheckedChangeListener { view, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "IS ON", Toast.LENGTH_SHORT).show()
                //
                object : CountDownTimer(3000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        de = Color.rgb(Color.red(de) - 60, Color.green(de) - 45, Color.blue(de) - 15)
                        mainL.setBackgroundColor(de)
                    }

                    override fun onFinish() {
                        switch1.isChecked = false
                    }
                }.start()

            } else {
                Toast.makeText(this, "IS OFF", Toast.LENGTH_SHORT).show()
            }
        }

        //informally declaring class
        //lambda is an anonymous function
        //single use function- skip having to use anonymous inner class
        selfDestruct.setOnClickListener {
            selfDestruct.isEnabled = false
            object : CountDownTimer(11000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    selfDestruct.setText("seconds remaining: " + millisUntilFinished / 1000)
                    c = Color.rgb(Color.red(c) - 6, Color.green(c) - 20, Color.blue(c) - 20)
                    mainL.setBackgroundColor(c)
                }

                override fun onFinish() {
                    selfDestruct.setText("done!")
                    finish()
                }
            }.start()
        }



        lookBusy.setOnClickListener {
            groupBase.visibility = View.GONE
            groupBusy.visibility = View.VISIBLE
            object : CountDownTimer(5200, 400) {
                override fun onTick(millisUntilFinished: Long) {
                    text.text = ("Code Compiling "+(100-millisUntilFinished.toInt() / 50)+ "% ...")
                    bar.progress = 100 - (millisUntilFinished.toInt() / 50)
                }

                override fun onFinish() {
                    groupBusy.visibility = View.GONE
                    groupBase.visibility = View.VISIBLE
                }
            }.start()


        }
    }


    //wire
    fun wired() {
        selfDestruct = findViewById(R.id.self_destruct)
        lookBusy = findViewById(R.id.look_busy)
        switch1 = findViewById(R.id.switch1)
        mainL = findViewById(R.id.main_layout)
        text = findViewById(R.id.textView)
        bar = findViewById(R.id.progressBar)
        groupBase = findViewById(R.id.base)
        groupBusy = findViewById(R.id.look_busy_g)

        groupBusy.visibility = View.GONE
    }
}