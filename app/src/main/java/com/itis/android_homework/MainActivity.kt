package com.itis.android_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.itis.android_homework.databinding.ActivityMainBinding
import com.itis.android_homework.fragments.*

private const val HOME = "home"
private const val SEARCH = "search"
private const val REELS = "reels"
private const val ACTIVITY = "activity"
private const val USER = "user"

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var fragments: Map<String, Fragment>? = null
    private var tags: Map<String, String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setFragments()
        setTags()

        binding?.apply {
            imgBtnHome.run {
                setAction(this, getFragmentByName(HOME), getTagByName(HOME))
            }
            imgBtnSearch.run {
                setAction(this, getFragmentByName(SEARCH), getTagByName(SEARCH))
            }
            imgBtnReels.run {
                setAction(this, getFragmentByName(REELS), getTagByName(REELS))
            }
            imgBtnActivity.run {
                setAction(this, getFragmentByName(ACTIVITY), getTagByName(ACTIVITY))
            }
            imgBtnProfile.run {
                setAction(this, getFragmentByName(USER), getTagByName(USER))
            }
        }
    }

    private fun setAction(view: View, nextFragment: Fragment?, tag: String?) {
        tag?.also {
            nextFragment?.run {
                view.setOnClickListener {
                    navigateTo(this, tag)
                }
            }
        }
    }

    private fun navigateTo(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().run {
            setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            replace(R.id.container, fragment)
            addToBackStack(tag)
            commit()
        }
    }

    private fun getTagByName(name: String): String? {
        return tags?.let {
            it[name]
        }
    }

    private fun getFragmentByName(name: String): Fragment? {
        return fragments?.let {
            it[name]
        }
    }

    private fun setFragments() {
        fragments = hashMapOf(
            HOME to HomeFragment(),
            SEARCH to SearchFragment(),
            REELS to ReelsFragment(),
            ACTIVITY to ActivityFragment(),
            USER to UserFragment(),
        )
    }

    private fun setTags() {
        tags = hashMapOf(
            HOME to "tag_home",
            SEARCH to "tag_search",
            REELS to "tag_reels",
            ACTIVITY to "tag_activity",
            USER to "tag_user",
        )
    }
}
