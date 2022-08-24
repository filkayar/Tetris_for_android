package com.example.tetris

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.tetris.storage.AppPreferences
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    var tvHighScore: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val preferences = AppPreferences(this)

        val btnNewGame = findViewById<Button>(R.id.btn_new_game)
        val btnResetScore = findViewById<Button>(R.id.btn_reset_score)
        val btnExit = findViewById<Button>(R.id.btn_exit)
        tvHighScore = findViewById<TextView>(R.id.tv_high_score)
        tvHighScore?.text = "High score: ${preferences.getHighScore()}"

        btnNewGame.setOnClickListener(this::onBtnNewGameClick)
        btnResetScore.setOnClickListener(this::onBtnResetScoreClick)
        btnExit.setOnClickListener(this::onBtnExit)
    }

    private fun onBtnNewGameClick(view: View) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    private fun onBtnResetScoreClick(view: View) {
        val preferences = AppPreferences(this)
        preferences.clearHighScore()
        Snackbar.make(view, "Score successfully reset", Snackbar.LENGTH_SHORT).show()
        tvHighScore?.text = "High score: ${preferences.getHighScore()}"
    }

    private fun onBtnExit(view: View) {
        exitProcess(0)
    }
}