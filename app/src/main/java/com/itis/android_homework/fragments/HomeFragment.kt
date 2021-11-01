package com.itis.android_homework.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itis.android_homework.adapter.DessertAdapter
import com.itis.android_homework.repositories.DessertRepository
import com.itis.android_homework.R
import androidx.recyclerview.widget.DividerItemDecoration
import com.itis.android_homework.MainActivity

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var dessertAdapter: DessertAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dessertAdapter = DessertAdapter(DessertRepository.desserts, Glide.with(this)) { id ->
            findNavController().navigate(
                R.id.action_homeFragment_to_dessertFragment,
                DessertFragment.createBundle(id)
            )
        }

        (activity as? MainActivity)?.supportActionBar?.apply {
            setTitle(R.string.list_name)
            setDisplayHomeAsUpEnabled(false)
        }

        view.findViewById<RecyclerView>(R.id.rv_cakes).run {
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL,
                )
            )
            adapter = dessertAdapter
        }
    }
}
