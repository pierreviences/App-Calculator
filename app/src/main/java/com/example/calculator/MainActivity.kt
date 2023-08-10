package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var lastNumeric = false
    var stateError = false
    var lastDot = false

    private lateinit var expression: Expression
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    fun onAllClearClick(view: View) {}
    fun onEqualClick(view: View) {}
    fun onDigitClick(view: View) {}
    fun onOperatorClick(view: View) {}
    fun onBackClick(view: View) {}
    fun onClearClick(view: View) {}

    fun OnEqual(){

    }
}