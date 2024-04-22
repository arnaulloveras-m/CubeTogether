package com.example.cubetogether

import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cubetogether.adapter.TiempoAdapter
import com.example.cubetogether.databinding.FragmentHomeBinding
import com.example.cubetogether.model.Tiempo
import com.example.cubetogether.model.TiempoBO
import com.example.cubetogether.viewModel.HomeViewModel
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.time.LocalDateTime

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private var db = Firebase.firestore

    private var number = 0

    private var adapter : TiempoAdapter? = null

    private lateinit var recyclerView: RecyclerView

    private val viewModel: HomeViewModel by viewModels();

    private var list: List<TiempoBO> = listOf(TiempoBO("", ""))
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )

        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavView)
        bottomNavigationView.visibility = View.VISIBLE

        val auth = FirebaseAuth.getInstance()
        val userLogged = auth.currentUser?.email.toString()

        recyclerView = binding.recyclerView

        initRecyclerView()

        db = FirebaseFirestore.getInstance()

        db.collection("times")
            .document(userLogged)
            .collection("perUsuari")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")

                        list += TiempoBO(document.data.toString().split("{data=")[1].split(',')[0],
                                document.data.toString().split("tiempo=")[1].split('}')[0] + "s")

                }
                println(list)
                adapter = (TiempoAdapter(list))
                recyclerView.adapter = adapter
            }


        binding.butoInserir.setOnClickListener {
            val fecha = LocalDateTime.now()

            val hora = fecha.hour + 1
            val minut = fecha.minute.toString()
            val segon = fecha.second.toString()

            val horaActual = hora.toString() + ":" + minut + ":" + segon

            var max = userLogged + db.collection("times").count().toString() + number.toString()
            number++
                db.collection("times").document(userLogged).collection("perUsuari").document(max).set(
                    hashMapOf("tiempo" to binding.tiempo.text.toString().toInt(),
                        "data" to horaActual)
                )

            db.collection("times")
                .document(userLogged)
                .collection("perUsuari")
                .get()
                .addOnSuccessListener { documents ->
                    list = emptyList()
                    for (document in documents) {
                        Log.d(TAG, "${document.id} => ${document.data}")

                        list += listOf(
                            TiempoBO(document.data.toString().split("{data=")[1].split(',')[0],
                                document.data.toString().split("tiempo=")[1].split('}')[0] + "s")
                        )
                    }
                    println(list)
                    adapter = (TiempoAdapter(list))
                    recyclerView.adapter = adapter
                }

        }

        return binding.root
    }

    private fun setTiempoListObserver() {
        viewModel.getTiempoListLiveData().removeObservers(this)
        viewModel.getTiempoListLiveData().observe(this, { list ->
            updateTiempoList(list)
        })
    }

    private fun updateTiempoList(list: List<TiempoBO>) {
        adapter?.updateDataSet(list)
    }

    private fun initRecyclerView(){
        val manager = LinearLayoutManager(this.context)
        val decoration = DividerItemDecoration(this.context, manager.orientation)
        binding.recyclerView.layoutManager = manager

        binding.recyclerView.addItemDecoration(decoration)
    }

    private fun setUpViews() {
        val list = mutableListOf<TiempoBO>()
        adapter = TiempoAdapter(list)
        binding?.recyclerView?.adapter = adapter
    }



}

