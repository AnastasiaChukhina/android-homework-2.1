package com.itis.android_homework.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.android_homework.MainActivity
import com.itis.android_homework.R

class HomeFragment : Fragment(R.layout.home_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
        }
    }
}
