package com.example.agecalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDateText: TextView? = null
    private var ageInHourText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPickDate: Button = findViewById(R.id.btnPickDate)
        selectedDateText = findViewById(R.id.selectedDateText)
        ageInHourText = findViewById(R.id.ageInHourText)

        btnPickDate.setOnClickListener {
            clickDatePicker()
        }


    }
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val datePD = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "$selectedYear , ${selectedMonth+1} , $selectedDayOfMonth", Toast.LENGTH_LONG).show()
            val formattedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                selectedDateText?.text = formattedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                val finalDate = sdf.parse(formattedDate)
                finalDate?.let{
                    val selectedDateInHour = finalDate.time / 3600000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {
                        val currentDateInHour = currentDate.time / 3600000

                        val differenceInHour = currentDateInHour - selectedDateInHour

                        ageInHourText?.text = differenceInHour.toString()
                    }
                }




            },
            year,
            month,
            day
            )
        datePD.datePicker.maxDate = System.currentTimeMillis() - 86400000

        datePD.show()



    }
}