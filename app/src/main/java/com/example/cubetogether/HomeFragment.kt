package com.example.cubetogether

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.cubetogether.databinding.FragmentHomeBinding
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.firestore

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private val db = Firebase.firestore

    private var number = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )

        binding.butoInserir.setOnClickListener {
            var max = number.toString()
            number++
            db.collection("times").document(max).set(
                hashMapOf("tiempo" to binding.tiempo.text.toString().toInt())
            )
        }

        return binding.root
    }

}