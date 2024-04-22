package com.example.cubetogether

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.cubetogether.databinding.FragmentMetodesBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MetodesFragment : Fragment() {

    private lateinit var binding: FragmentMetodesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavView)
        bottomNavigationView.visibility = View.VISIBLE

        val video = binding.videoView
        video.setVideoPath("https://www.youtube.com/watch?v=hqL1vHCqb2U&t=32s")
        video.start()

        return binding.root
    }

}