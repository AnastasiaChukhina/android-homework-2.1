package com.itis.android_homework

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import com.itis.android_homework.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        supportActionBar?.hide()

        binding.btnEditProfileName.setOnClickListener {
            changeNickname(it)
        }
    }

    private fun changeNickname(view: View) {
        shoeEditText()
        binding.btnDone.setOnClickListener {
            showUsername()
            closeTab(it)
        }
    }

    private fun closeTab(view: View?) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun shoeEditText(){
        binding.apply {
            tvNick.visibility = View.GONE
            etUsername.visibility = View.VISIBLE
            btnDone.visibility = View.VISIBLE
        }
    }

    private fun showUsername(){
        binding.apply {
            tvNick.text = etUsername.text
            tvNick.visibility = View.VISIBLE
            etUsername.visibility = View.GONE
            etUsername.text.clear()
            btnDone.visibility = View.GONE
        }
    }
}
