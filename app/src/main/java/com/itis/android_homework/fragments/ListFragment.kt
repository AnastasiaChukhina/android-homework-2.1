package com.itis.android_homework.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.itis.android_homework.MainActivity
import com.itis.android_homework.R
import com.itis.android_homework.adapter.recyclerView.FruitRVAdapter
import com.itis.android_homework.databinding.ListFragmentBinding
import com.itis.android_homework.item_decorator.SpaceItemDecorator
import com.itis.android_homework.models.Fruit
import com.itis.android_homework.repositories.FruitRepository
import com.itis.android_homework.callbacks.SwipeToDeleteCallback as SwipeToDeleteCallback

class ListFragment : Fragment(R.layout.list_fragment) {

    private var binding: ListFragmentBinding? = null
    private var fruitAdapter: FruitRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListFragmentBinding.bind(view)
        fruitAdapter = FruitRVAdapter(Glide.with(this)) {
            deleteItem(it)
        }
        fruitAdapter?.submitList(FruitRepository.fruits)

        with(binding) {
            this?.rvFruits?.run {
                adapter = fruitAdapter
                addItemDecoration(
                    DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
                )
                addItemDecoration(
                    SpaceItemDecorator(requireContext())
                )

                ItemTouchHelper(SwipeToDeleteCallback() {
                    deleteItem(it)
                }).attachToRecyclerView(this)
            }

            this?.fabAdd?.setOnClickListener {
                showDialog()
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

    private fun showDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.add_dialog, null)
        createAddDialog(dialogView)
    }

    private fun createAddDialog(view: View) {
        AlertDialog.Builder(requireContext())
            .setTitle("Add new fruit:")
            .setView(view)
            .setPositiveButton("SAVE") { _, _ ->
                checkData(view)
            }
            .setNegativeButton("CANCEL") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun deleteItem(it: Int) {
        FruitRepository.deleteById(it)
        updateList(FruitRepository.fruits)
    }

    private fun checkData(view: View) {
        val name = view.findViewById<TextInputEditText>(R.id.et_name).text.toString()
        val vitamins = view.findViewById<TextInputEditText>(R.id.et_vitamins).text.toString()
        val position =
            setPosition(view.findViewById<TextInputEditText>(R.id.et_position).text.toString())
        if (name != "" && vitamins != "") {
            FruitRepository.addNewItem(position, name, vitamins)
            updateList(FruitRepository.fruits)
        }
    }

    private fun setPosition(position: String): Int {
        val listSize = FruitRepository.fruits.size
        if (position != "") {
            val index = position.toInt()
            if (index < 0) return 0
            if (index > listSize) return listSize
            return index
        }
        return listSize
    }

    private fun updateList(fruits: MutableList<Fruit>) {
        fruitAdapter?.submitList(fruits)
    }
}
