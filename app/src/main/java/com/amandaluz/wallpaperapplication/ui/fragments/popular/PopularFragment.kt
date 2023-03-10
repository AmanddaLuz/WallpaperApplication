package com.amandaluz.wallpaperapplication.ui.fragments.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.amandaluz.wallpaperapplication.databinding.FragmentPopularBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : Fragment() {
    private lateinit var binding : FragmentPopularBinding

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle?
    ) : View {
        binding = FragmentPopularBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
    }
}