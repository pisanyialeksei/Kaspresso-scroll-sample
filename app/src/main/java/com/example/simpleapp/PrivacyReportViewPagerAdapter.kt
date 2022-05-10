package com.example.simpleapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PrivacyReportViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val context: Context
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount() = 5

    override fun getItem(position: Int): Fragment {
        return PrivacyReportPageFragment()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return position.toString()
    }
}