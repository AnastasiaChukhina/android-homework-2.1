package com.itis.android_homework.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.android_homework.R
import com.itis.android_homework.databinding.FragmentActivityBinding

class ActivityFragment: Fragment(R.layout.fragment_activity) {
    private var binding: FragmentActivityBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentActivityBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
