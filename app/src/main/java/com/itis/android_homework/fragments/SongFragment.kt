package com.itis.android_homework.fragments

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.android_homework.MainActivity
import com.itis.android_homework.R
import com.itis.android_homework.databinding.SongFragmentBinding
import com.itis.android_homework.repositories.SongRepository
import com.itis.android_homework.services.MusicService

class SongFragment : Fragment(R.layout.song_fragment) {

    private var binding: SongFragmentBinding? = null
    private var musicService: MusicService? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            musicService = (service as? MusicService.MusicBinder)?.getService()
            if (musicService != null) {
                initListeners()
                arguments?.getInt("ARG_SONG_ID")?.let { id ->
                    setView(id)
                }
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            musicService = null
        }
    }

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

    override fun onResume() {
        super.onResume()
        val intent = Intent(context, MusicService::class.java)
        activity?.bindService(
            intent,
            connection,
            Context.BIND_AUTO_CREATE
        )
        musicService?.getCurrentId()?.also {
            setView(it)
        }
    }

    private fun setView(id: Int) {
        val current = SongRepository.songs[id]

        binding?.apply {
            tvSongName.text = current.title
            tvAuthor.text = current.author
            ivCover.setImageResource(current.cover)
        }
        if (getCurrentId() != id || id == SongRepository.DEFAULT_SONG_ID) {
            startMusic(id)
            setPlayView()
        }
        else if(!isPlaying()) setPauseView()
        else setPlayView()
    }

    private fun startMusic(id: Int) {
        musicService?.setSong(id)
        musicService?.play()
    }

    private fun getCurrentId(): Int? = musicService?.getCurrentId()

    private fun isPlaying(): Boolean = musicService?.isPlaying() == true

    private fun initListeners() {
        binding?.apply {
            imgBtnPause.setOnClickListener {
                musicService?.pause()
                setPauseView()
            }
            imgBtnPlay.setOnClickListener {
                musicService?.play()
                setPlayView()
            }
            imgBtnFastRewind.setOnClickListener {
                musicService?.prev()
                setView(musicService?.getCurrentId() ?: SongRepository.DEFAULT_SONG_ID)
            }
            imgBtnFastForward.setOnClickListener {
                musicService?.next()
                setView(musicService?.getCurrentId() ?: SongRepository.DEFAULT_SONG_ID)
            }
            imgBtnStop.setOnClickListener {
                musicService?.stop()
                setPauseView()
            }
        }
    }

    private fun setPauseView() {
        binding?.apply {
            imgBtnPause.visibility = View.INVISIBLE
            imgBtnPlay.visibility = View.VISIBLE
        }
    }

    private fun setPlayView() {
        binding?.apply {
            imgBtnPlay.visibility = View.INVISIBLE
            imgBtnPause.visibility = View.VISIBLE
        }
    }
}
