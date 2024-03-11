package com.example.cubetogether

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.cubetogether.adapter.TiempoAdapter
import com.example.cubetogether.databinding.ActivityHomeBinding
import com.example.cubetogether.model.Tiempo
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.time.LocalDateTime

enum class ProviderType {
    BASIC
}

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    private var db = Firebase.firestore

    private var number = 1

    private lateinit var tiempoAdapter: TiempoAdapter

    private lateinit var tiempoList: List<Tiempo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        //Setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")

        supportFragmentManager.commit {

            replace<HomeFragment>(R.id.Frames)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

    }

}