package com.example.cubetogether

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.cubetogether.databinding.FragmentAuthBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class AuthFragment : Fragment() {

    private var db = FirebaseFirestore.getInstance()

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_auth, container, false
        )

        val email = FirebaseAuth.getInstance().currentUser?.email.toString()

        binding.email.text = email

        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavView)
        bottomNavigationView.visibility = View.VISIBLE

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_authActivity)
        }

        binding.butoGuardar.setOnClickListener {
            db.collection("users").document(email).set(
                hashMapOf("Telefono" to binding.idTelefon.text.toString(),
                    "Ciudad" to binding.idCiutat.text.toString())
            )
        }

        binding.butoRecuperar.setOnClickListener {
            db.collection("users").document(email).get().addOnSuccessListener {
                binding.idTelefon.setText(it.get("Telefono") as String?)
                binding.idCiutat.setText(it.get("Ciudad") as String?)
            }
        }

        binding.butoEsborrar.setOnClickListener {
            db.collection("users").document(email).delete()
            binding.idTelefon.setText("")
            binding.idCiutat.setText("")
        }

        return binding.root
    }

}