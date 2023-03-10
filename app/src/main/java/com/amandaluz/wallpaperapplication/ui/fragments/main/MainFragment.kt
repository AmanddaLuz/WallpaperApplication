package com.amandaluz.wallpaperapplication.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.amandaluz.wallpaperapplication.databinding.FragmentMainBinding
import com.amandaluz.wallpaperapplication.ui.fragments.categories.CategoriesFragment
import com.amandaluz.wallpaperapplication.ui.fragments.collections.CollectionsFragment
import com.amandaluz.wallpaperapplication.ui.fragments.pageradapter.ViewPagerAdapter
import com.amandaluz.wallpaperapplication.ui.fragments.popular.PopularFragment
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    private val tabTitles = listOf("Popular,", "Collections", "Categories")
    private val fragments = listOf(PopularFragment(), CollectionsFragment(), CategoriesFragment())

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle?
    ) : View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        initToolbar()
        initViewPager()
        initTabLayout()
    }

    private fun initTabLayout(){
        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun initToolbar(){
        binding.toolbar.title = "Wallpapaers"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun initViewPager(){
        val pagerAdapter = ViewPagerAdapter(context as FragmentActivity, fragments)
        binding.run {
            viewPager.adapter = pagerAdapter
        }
    }

}