package com.itis.android_homework.fragments

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.android_homework.MainActivity
import com.itis.android_homework.R
import com.itis.android_homework.databinding.SongFragmentBinding

class SongFragment : Fragment(R.layout.song_fragment) {

    private lateinit var binding: SongFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = SongFragmentBinding.bind(view)

        (activity as? MainActivity)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            (activity as? MainActivity)?.onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
