package com.itis.android_homework.repositories

import com.itis.android_homework.R
import com.itis.android_homework.models.Song

object SongRepository {
    private var cursor = 0;

    val songs = arrayListOf(
        Song(
            cursor++,
            "Amusement Park",
            "Baekhyun",
            256,
            R.drawable.amusement_park,
            R.raw.amusement_park
        ),
        Song(
            cursor++,
            "Bad Guy",
            "Billie Eilish",
            194,
            R.drawable.bad_guy,
            R.raw.bad_guy
        ),
        Song(
            cursor++,
            "Bad Romance",
            "Lady Gaga",
            213,
            R.drawable.the_fame_monster,
            R.raw.bad_romance
        ),
        Song(
            cursor++,
            "Bambi",
            "Baekhyun",
            213,
            R.drawable.bambi,
            R.raw.bambi
        ),
        Song(
            cursor++,
            "Lalisa",
            "Lalisa",
            200,
            R.drawable.lalisa,
            R.raw.lalisa
        ),
        Song(
            cursor++,
            "Money",
            "Lalisa",
            200,
            R.drawable.lalisa,
            R.raw.money
        ),
        Song(
            cursor++,
            "Ocean Eyes",
            "Billie Eilish",
            200,
            R.drawable.ocean_eyes,
            R.raw.ocean_eyes
        ),
        Song(
            cursor++,
            "Poker Face",
            "Lady Gaga",
            237,
            R.drawable.the_fame_monster,
            R.raw.poker_face
        ),
        Song(
            cursor++,
            "Siempre Me Quedara",
            "Bebe",
            207,
            R.drawable.siempre_me_quedara,
            R.raw.siempre_me_quedara
        ),
        Song(
            cursor++,
            "Therefore I Am",
            "Billie Eilish",
            174,
            R.drawable.therefore_i_am,
            R.raw.therefore_i_am
        ),
        Song(
            cursor++,
            "Tie a Cherry",
            "CL",
            190,
            R.drawable.tie_a_cherry,
            R.raw.tie_a_cherry
        ),
        Song(
            cursor++,
            "UN Village",
            "Baekhyun",
            235,
            R.drawable.un_village,
            R.raw.un_village
        )
    )

    fun getSongById(id: Int): Song = songs[id]
}
