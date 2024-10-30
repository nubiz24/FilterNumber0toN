package com.example.bai1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioEven = findViewById<RadioButton>(R.id.radioEven)
        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        val radioSquare = findViewById<RadioButton>(R.id.radioSquare)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val listView = findViewById<ListView>(R.id.listView)
        val textViewError = findViewById<TextView>(R.id.textViewError)

        buttonShow.setOnClickListener {
            val input = editTextNumber.text.toString()
            textViewError.text = ""  // Clear any previous error message

            // Kiểm tra đầu vào hợp lệ
            val n = input.toIntOrNull()
            if (n == null || n < 0) {
                textViewError.text = "Vui lòng nhập một số nguyên dương."
                return@setOnClickListener
            }

            val resultList = when {
                radioEven.isChecked -> getEvenNumbers(n)
                radioOdd.isChecked -> getOddNumbers(n)
                radioSquare.isChecked -> getSquareNumbers(n)
                else -> emptyList()  // Không chọn loại số nào
            }

            // Hiển thị danh sách kết quả lên ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listView.adapter = adapter
        }
    }

    // Hàm trả về danh sách các số chẵn từ 0 đến n
    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    // Hàm trả về danh sách các số lẻ từ 1 đến n
    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    // Hàm trả về danh sách các số chính phương từ 0 đến n
    private fun getSquareNumbers(n: Int): List<Int> {
        val result = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            result.add(i * i)
            i++
        }
        return result
    }
}
