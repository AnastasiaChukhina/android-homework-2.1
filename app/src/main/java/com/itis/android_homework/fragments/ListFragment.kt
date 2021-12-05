package com.itis.android_homework.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.itis.android_homework.MainActivity
import com.itis.android_homework.R
import com.itis.android_homework.databinding.ListFragmentBinding
import com.itis.android_homework.item_decorators.SpaceItemDecorator
import com.itis.android_homework.recycler_view.SongAdapter
import com.itis.android_homework.repositories.SongRepository

class ListFragment : Fragment(R.layout.list_fragment) {

    private var binding: ListFragmentBinding? = null
    private var songAdapter: SongAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ListFragmentBinding.bind(view)
        songAdapter = SongAdapter(SongRepository.songs){
            showSongFragment(it)
        }
        with(binding){
            this?.rvSongs?.run {
                adapter = songAdapter
                addItemDecoration(
                    DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
                )
                addItemDecoration(
                    SpaceItemDecorator(requireContext())
                )
            }

            this?.fabPlay?.setOnClickListener {
                showSongFragment(SongRepository.DEFAULT_SONG_ID)
            }
        }

        (activity as? MainActivity)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun showSongFragment(id: Int){
        val bundle = Bundle().apply {
            putInt("ARG_SONG_ID", id)
        }
        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.enter_from_right)
            .setExitAnim(R.anim.exit_to_left)
            .setPopEnterAnim(R.anim.enter_from_left)
            .setPopExitAnim(R.anim.exit_to_right)
            .build()

        findNavController().navigate(
            R.id.action_listFragment_to_songFragment,
            bundle,
            options
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
