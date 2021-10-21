package com.itis.android_homework.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.android_homework.R
import com.itis.android_homework.databinding.FragmentUserBinding

class UserFragment: Fragment(R.layout.fragment_user) {
    private var binding: FragmentUserBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
