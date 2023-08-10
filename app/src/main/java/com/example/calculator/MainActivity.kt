package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lastNumeric = false
    private var stateError = false
    private var lastDot = false
    private lateinit var expression: Expression

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onAllClearClick(view: View) {
        clearInput()
    }

    fun onEqualClick(view: View) {
        if (lastNumeric && !stateError) {
            val inputText = binding.dataTv.text.toString()
            expression = try {
                ExpressionBuilder(inputText).build()
            } catch (ex: Exception) {
                handleExpressionError(ex)
                return
            }

            try {
                val result = expression.evaluate()
                binding.resultTv.visibility = View.VISIBLE
                binding.resultTv.text = "=$result"
            } catch (ex: ArithmeticException) {
                handleExpressionError(ex)
            }
        }
    }

    fun onDigitClick(view: View) {
        if (stateError) {
            clearInput()
        }
        val buttonText = (view as Button).text.toString()
        binding.dataTv.append(buttonText)
        lastNumeric = true
    }

    fun onOperatorClick(view: View) {
        if (!stateError && lastNumeric) {
            val buttonText = (view as Button).text.toString()
            binding.dataTv.append(buttonText)
            lastDot = false
            lastNumeric = false
        }
    }

    fun onBackClick(view: View) {
        val currentInput = binding.dataTv.text.toString()
        if (currentInput.isNotEmpty()) {
            binding.dataTv.text = currentInput.dropLast(1)
            if (currentInput.last().isDigit()) {
                lastNumeric = true
            }
        }
        if (binding.dataTv.text.isEmpty()) {
            binding.resultTv.text = ""
            binding.resultTv.visibility = View.GONE
        }
    }

    fun onClearClick(view: View) {
        clearInput()
    }

    private fun clearInput() {
        binding.dataTv.text = ""
        binding.resultTv.text = ""
        stateError = false
        lastDot = false
        lastNumeric = false
        binding.resultTv.visibility = View.GONE
    }

    private fun handleExpressionError(ex: Exception) {
        Log.e("Evaluate Error", ex.toString())
        binding.resultTv.text = "Error"
        stateError = true
        lastNumeric = false
    }
}
