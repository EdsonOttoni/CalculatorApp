package com.example.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add buttons numbers
        button_one.setOnClickListener { addMathematicalExpression("1", true) }
        button_two.setOnClickListener { addMathematicalExpression("2", true) }
        button_three.setOnClickListener { addMathematicalExpression("3", true) }
        button_four.setOnClickListener { addMathematicalExpression("4", true) }
        button_five.setOnClickListener { addMathematicalExpression("5", true) }
        button_six.setOnClickListener { addMathematicalExpression("6", true) }
        button_seven.setOnClickListener { addMathematicalExpression("7", true) }
        button_eight.setOnClickListener { addMathematicalExpression("8", true) }
        button_nine.setOnClickListener { addMathematicalExpression("9", true) }
        button_zero.setOnClickListener { addMathematicalExpression("0", true) }

        // Add dot
        button_dot.setOnClickListener {
            val string = text_expression.text.toString()
            when {
                 string.isEmpty() -> {
                    addMathematicalExpression("0.", true)
                }
                string.contains(".") -> {
                    return@setOnClickListener
                }
                else -> {
                    addMathematicalExpression(".", true)
                }
            }
        }

        // Add buttons operation
        button_plus.setOnClickListener { addMathematicalExpression("+", false) }
        button_minus.setOnClickListener { addMathematicalExpression("-", false) }
        button_division.setOnClickListener { addMathematicalExpression("/", false) }
        button_multiplication.setOnClickListener { addMathematicalExpression("x", false) }

        // Add clean screen button
        button_clear.setOnClickListener {
            val string = text_expression.text.toString()

            if (string.isNotEmpty()) {
                text_expression.text = string.substring(0, string.length - 1)
            }
            text_result.text = ""
        }

        button_clear_all.setOnClickListener {
            text_expression.text = ""
            text_result.text = ""
        }

        button_equal.setOnClickListener {
            val string = text_expression.text.toString()
            val result = calculation(string)
            text_result.text = result.toString()
        }
    }

    private fun addMathematicalExpression(string: String, cleanData: Boolean) {

        if (text_result.text.isNotEmpty()) {
            text_expression.text = ""
        }

        if (cleanData) {
            text_result.text = ""
            text_expression.append(string)
        } else {
            text_expression.append(text_result.text)
            text_expression.append(string)
            text_result.text = ""
        }
    }

    private fun calculation(string: String): Double {
        var result = 0.0
        val numbers = string.split("[-+/x]".toRegex()).toTypedArray()
        val op = string.split("\\d".toRegex()).toTypedArray()
        var i = 1

        result += numbers[0].trim().toDouble()
        for (itemOp in op) {
            if (itemOp.contains("[-+/x]".toRegex())) {
                when (itemOp) {
                    "+" -> {
                        result += numbers[i].trim().toDouble()
                    }
                    "-" -> {
                        result -= numbers[i].trim().toDouble()
                    }
                    "x" -> {
                        result *= numbers[i].trim().toDouble()
                    }
                    "/" -> {
                        result /= numbers[i].trim().toDouble()
                    }
                }
                i++
            }
        }
        return result
    }
}