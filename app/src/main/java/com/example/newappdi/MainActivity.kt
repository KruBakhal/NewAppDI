package com.example.newappdi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.newappdi.api_calling.APICallActivity
import com.example.newappdi.newapp.NewsActivity
import com.example.newappdi.quizgame.GameActivity
import com.example.newappdi.tictacto.TicTacTOActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view1 = findViewById<View>(R.id.new_app).apply {
            setOnClickListener {
                startActivity(Intent(this@MainActivity, NewsActivity::class.java))
            }
        }
        val view2 = findViewById<View>(R.id.api_call).apply {
            setOnClickListener {
                startActivity(Intent(this@MainActivity, APICallActivity::class.java))
            }
        }
         findViewById<View>(R.id.tctct).apply {
            setOnClickListener {
                startActivity(Intent(this@MainActivity, TicTacTOActivity::class.java))
            }
        }
         findViewById<View>(R.id.game).apply {
            setOnClickListener {
                startActivity(Intent(this@MainActivity, GameActivity::class.java))
            }
        }
    }
}