package com.example.databinding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class CustomPageAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    private val fragmentMap = mutableMapOf<Int, Fragment>()

    override fun getItem(position: Int): Fragment {
        var fragment = fragmentMap[position]
        if (fragment == null) {
            if (position == 0) {
                fragment = NoteListFragment()
            } else {
                fragment = SettingFragment()
            }
            fragmentMap.put(position, fragment)
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }
}