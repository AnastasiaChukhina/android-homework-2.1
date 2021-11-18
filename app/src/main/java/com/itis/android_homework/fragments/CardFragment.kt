package com.itis.android_homework.fragments

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itis.android_homework.MainActivity
import com.itis.android_homework.R
import com.itis.android_homework.adapter.cardView.FruitCVAdapter
import com.itis.android_homework.databinding.CardFragmentBinding
import com.itis.android_homework.item_decorator.SpaceItemDecorator
import com.itis.android_homework.repositories.FruitRepository

class CardFragment : Fragment(R.layout.card_fragment) {

    private var fruitCVAdapter: FruitCVAdapter? = null
    private var binding: CardFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = CardFragmentBinding.bind(view)
        fruitCVAdapter = FruitCVAdapter(FruitRepository.fruits, Glide.with(this))

        with(binding){
            this?.rvFruits?.run{
                adapter = fruitCVAdapter
                addItemDecoration(
                    DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
                )
                addItemDecoration(
                    SpaceItemDecorator(requireContext())
                )
            }
        }

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
