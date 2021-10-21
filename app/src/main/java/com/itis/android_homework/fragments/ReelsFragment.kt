package com.itis.android_homework.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.android_homework.R
import com.itis.android_homework.databinding.FragmentReelsBinding

class ReelsFragment: Fragment(R.layout.fragment_reels) {
    private var binding: FragmentReelsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReelsBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
