package com.example.testapplication.inbox

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testapplication.BaseFragment
import com.example.testapplication.R
import com.example.testapplication.databinding.InboxFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class InboxFragment : BaseFragment(R.layout.inbox_fragment) {

    private val viewModel: InboxViewModel by viewModels()

    private val binding by lazy { InboxFragmentBinding.bind(requireView()) }
    private val viewPagerAdapter by lazy { ViewPagerAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel

        with(binding) {
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    1 -> "InProgress"
                    2 -> "Completed"
                    3 -> "Ended"
                    else -> "New"
                }
            }.attach()
            // viewModel.viewPagerState?.let {
            //     viewPagerAdapter.restoreState(it)
            // }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // viewModel.viewPagerState = viewPagerAdapter.saveState()
    }
}

class ViewPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(
    fragment
) {

    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> InProgressFragment()
            2 -> CompletedFragment()
            3 -> EndedFragment()
            else -> NewFragment()
        }
    }
}