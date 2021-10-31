package com.danialtavakoli.projects.calculatorx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.danialtavakoli.projects.calculatorx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isLastInputNumber = false
    private var hasDotInTextView = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }

    fun onDigitClicked(view: View) {
        val clickedButton = view as Button
        binding.textViewInput.append(clickedButton.text)
        isLastInputNumber = true
    }

    fun clearText(view: View) {
        binding.textViewInput.text = ""
        isLastInputNumber = false
        hasDotInTextView = false
    }

    fun onDecimalPointClick(view: View) {
        if (isLastInputNumber && !hasDotInTextView) {
            binding.textViewInput.append(".")
            hasDotInTextView = true
        }
    }

    fun onOperatorClick(view: View) {
        val button = view as Button
        if (isLastInputNumber && !isOperatorSelected(binding.textViewInput.text.toString())) {
            binding.textViewInput.append(button.text)
            isLastInputNumber = false
            hasDotInTextView = false
        }
    }

    private fun isOperatorSelected(text: String): Boolean {
        if (text.startsWith("-")) return false
        return text.contains("+") || text.contains("-") || text.contains("/") || text.contains("*")
    }

    fun onEqualClick(view: View) {
        var prefix = ""
        if (!isLastInputNumber) return
        var inputValue = binding.textViewInput.text.toString()
        if (inputValue.startsWith("-")) {
            prefix = "-"
            inputValue = inputValue.substring(1)
        }
        when {
            inputValue.contains("+") -> {
                val splitValueArray = inputValue.split("+")
                var firstNumber = splitValueArray[0]
                val secondNumber = splitValueArray[1]
                if (prefix.isNotEmpty()) firstNumber = prefix + firstNumber
                val result = firstNumber.toDouble() + secondNumber.toDouble()
                binding.textViewInput.text = result.toString()
            }
            inputValue.contains("*") -> {
                val splitValueArray = inputValue.split("*")
                var firstNumber = splitValueArray[0]
                val secondNumber = splitValueArray[1]
                if (prefix.isNotEmpty()) firstNumber = prefix + firstNumber
                val result = firstNumber.toDouble() * secondNumber.toDouble()
                binding.textViewInput.text = result.toString()
            }
            inputValue.contains("/") -> {
                val splitValueArray = inputValue.split("/")
                var firstNumber = splitValueArray[0]
                val secondNumber = splitValueArray[1]
                if (prefix.isNotEmpty()) firstNumber = prefix + firstNumber
                val result = firstNumber.toDouble() / secondNumber.toDouble()
                binding.textViewInput.text = result.toString()
            }
            inputValue.contains("-") -> {
                val splitValueArray = inputValue.split("-")
                var firstNumber = splitValueArray[0]
                val secondNumber = splitValueArray[1]
                if (prefix.isNotEmpty()) firstNumber = prefix + firstNumber
                val result = firstNumber.toDouble() - secondNumber.toDouble()
                binding.textViewInput.text = result.toString()
            }
            inputValue.contains("@") -> {
                val splitValueArray = inputValue.split("@")
                var firstNumber = splitValueArray[0]
                val secondNumber = splitValueArray[1]
                if (prefix.isNotEmpty()) firstNumber = prefix + firstNumber
                val result = (firstNumber.toDouble() / secondNumber.toDouble()) * 100
                binding.textViewInput.text = result.toString()
            }
            inputValue.contains("%") -> {
                val splitValueArray = inputValue.split("%")
                var firstNumber = splitValueArray[0]
                val secondNumber = splitValueArray[1]
                if (prefix.isNotEmpty()) firstNumber = prefix + firstNumber
                val result = firstNumber.toDouble() % secondNumber.toDouble()
                binding.textViewInput.text = result.toString()
            }
        }
    }
}