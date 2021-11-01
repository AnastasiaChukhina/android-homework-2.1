package com.itis.android_homework.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.itis.android_homework.MainActivity
import com.itis.android_homework.databinding.FragmentDessertBinding
import com.itis.android_homework.repositories.DessertRepository

class DessertFragment : Fragment(){

    private var binding : FragmentDessertBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDessertBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("ARG_ID")?.also {
            val id = it
            binding?.apply {
                tvName.text = DessertRepository.desserts[id].name
                tvCountry.text = DessertRepository.desserts[id].homeland
                Glide.with(
                    this@DessertFragment)
                    .load(DessertRepository.desserts[id].url)
                    .into(ivImage
                    )
            }
            (activity as? MainActivity)?.supportActionBar?.apply {
                title = DessertRepository.desserts[id].name
                setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            (activity as? MainActivity)?.onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    companion object {

        private const val ARG_ID = "ARG_ID"

        fun createBundle(id: Int): Bundle = Bundle().apply {
            putInt(ARG_ID, id)
        }
    }
}
