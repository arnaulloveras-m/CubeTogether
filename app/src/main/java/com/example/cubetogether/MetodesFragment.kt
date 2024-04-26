package com.example.cubetogether

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.MediaController
import androidx.databinding.DataBindingUtil
import com.example.cubetogether.databinding.FragmentMetodesBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MetodesFragment : Fragment() {

    private lateinit var binding: FragmentMetodesBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_metodes, container, false
        )
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavView)
        bottomNavigationView.visibility = View.VISIBLE

        val webView: WebView = binding.videoView

        val stringVideo = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/hqL1vHCqb2U?si=lUqLcTZP47Fn9d1t\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"

        webView.loadData(stringVideo, "text/html", "utf-8")

        webView.settings.javaScriptEnabled = true

        webView.webChromeClient = WebChromeClient()

        val video = binding.videoView

        return binding.root
    }

}