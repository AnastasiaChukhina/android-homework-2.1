package com.itis.android_homework.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.android_homework.MainActivity
import com.itis.android_homework.R
import com.itis.android_homework.databinding.ListFragmentBinding

class ListFragment : Fragment(R.layout.list_fragment) {

    private lateinit var bundle: ListFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bundle = ListFragmentBinding.bind(view)

        (activity as? MainActivity)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }
}
