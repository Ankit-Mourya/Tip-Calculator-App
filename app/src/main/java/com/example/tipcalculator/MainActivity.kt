package com.example.tipcalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

lateinit var binding : ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
        binding.plainTextInputEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view,keyCode) }
    }
   fun calculateTip(){
     val  stringInTextFeild = binding.plainTextInputEditText.text.toString()
       val cost = stringInTextFeild.toDouble()
       val selectedId = binding.tipOption.checkedRadioButtonId
       val tipPercentage = when(selectedId){
           R.id.option_twenty_percent -> 0.20
           R.id.option_eighteen_percent -> 0.18
           else -> 0.15
       }
       var tip = tipPercentage*cost
       val roundUp = binding.roundTheTip.isChecked
       if (roundUp){
           tip =kotlin.math.ceil(tip)
       }
       val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
       binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
   }
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}