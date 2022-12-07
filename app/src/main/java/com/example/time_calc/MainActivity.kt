package com.example.time_calc

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    var cal1:ImageView?=null
    var cal2:ImageView?=null
    var firstDate:TextView?=null
    var secDate:TextView?=null
    var dResult:TextView?=null
    var calc:AppCompatButton?=null



    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val btn2=findViewById<Button>(R.id.btn2)
        btn2.setOnClickListener{
            val dialogBinding=layoutInflater.inflate(R.layout.d_box,null)
            val myDlg=Dialog(this)
            myDlg.setContentView(dialogBinding)
            myDlg.setCancelable(true)
            myDlg.window?.setBackgroundDrawable(ColorDrawable(Color.BLUE))
            myDlg.show()
            val okbtn=dialogBinding.findViewById<Button>(R.id.alert_yes)
            okbtn.setOnClickListener{
                myDlg.dismiss()
            }

        }
        cal1=findViewById(R.id.cal1)
        cal2=findViewById(R.id.cal2)
        firstDate=findViewById(R.id.Date1)
        secDate=findViewById(R.id.Date2)
        dResult=findViewById(R.id.dResult)
        calc=findViewById(R.id.btn)

        cal1!!.setOnClickListener({view->
            ClickDatePicker(view,firstDate)
        })
        cal2!!.setOnClickListener({view->
            ClickDatePicker(view,secDate)
        })
        calc!!.setOnClickListener({view->
            CalculateDay(firstDate,secDate)
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)

    private fun CalculateDay(firstDate: TextView?, secDate: TextView?) {
        val FDate=firstDate!!.getText().toString()
        val SDate=secDate!!.getText().toString()
        val sdf=SimpleDateFormat("dd/mm/yyyy",Locale.getDefault())


        try{
            val d1=sdf.parse(FDate)
            val d2=sdf.parse(SDate)

            if(d1>=d2)
            {
                val Ddiff: Long =abs(d1.time - d2.time)
                val daysDiff: Long = TimeUnit.DAYS.convert(Ddiff, TimeUnit.MILLISECONDS)
                dResult!!.setText("Days in Between : "+daysDiff+"Days")
            }
            if(d1<=d2){
                val Ddiff: Long =abs(d2.time - d1.time)
                val daysDiff: Long = TimeUnit.DAYS.convert(Ddiff, TimeUnit.MILLISECONDS)
                dResult!!.setText("Days in Between : "+daysDiff+"Days")
            }




        }catch (e:Exception)
        {
            e.stackTrace
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun ClickDatePicker(view: View?, textView:TextView?) {
        val mycalendar=Calendar.getInstance()
        val year=mycalendar.get(Calendar.YEAR)
        val month=mycalendar.get(Calendar.MONTH)
        val day=mycalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,DatePickerDialog.OnDateSetListener
        { view, year, month, day ->
            val selecteddate="$day/${month+1}/$year"
            textView!!.setText(selecteddate)
        },year,month,day).show()
    }
}