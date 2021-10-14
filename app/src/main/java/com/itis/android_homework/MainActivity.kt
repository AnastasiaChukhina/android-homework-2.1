package com.itis.android_homework

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import com.itis.android_homework.databinding.ActivityMainBinding

private const val REQUEST_CODE_EMAIL = 1

class MainActivity :AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
    }

    fun onClickSendEmail(view: View) {
        with(binding){
            val to = arrayOf(etTo.text.toString())
            val subject = etCategory.text.toString()
            val text = etText.text.toString()
            if(to[0]!="" && subject != "" && text != ""){
                composeEmail(to, subject, text)
            }
            else{
                Snackbar.make(
                    root,
                    "Some fields are empty!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun composeEmail(to: Array<String>, subject: String, text: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, to)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, text)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_CODE_EMAIL)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_EMAIL && resultCode == Activity.RESULT_OK) {
            Snackbar.make(
                binding.root,
                "Created a new email",
                Snackbar.LENGTH_LONG
            ).show()
            hideKeyboard(binding.root)
        }
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
