package com.example.githubuserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView

class SplashScreen : AppCompatActivity() {
    private var progressBar: ProgressBar? = null
    private var i = 0
    private var txtView: TextView? = null
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)

        progressBar = findViewById(R.id.progress_Bar) as ProgressBar
        txtView = findViewById(R.id.text_view) as TextView
        i = progressBar!!.progress
        Thread(Runnable {
            while (i < 100) {
                i += 5
                handler.post(Runnable {
                    progressBar!!.progress = i
                    txtView!!.text = i.toString() + "/" + progressBar!!.max
                })
                try {
                    Thread.sleep(400)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        }).start()

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }, 8000)
    }
}