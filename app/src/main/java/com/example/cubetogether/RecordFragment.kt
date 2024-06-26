package com.example.cubetogether

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cubetogether.adapter.RecordAdapter
import com.example.cubetogether.databinding.FragmentRecordBinding
import com.example.cubetogether.model.RecordProvider
import com.google.android.material.bottomnavigation.BottomNavigationView

class RecordFragment : Fragment() {

    private lateinit var binding: FragmentRecordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_record, container, false
        )
        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavView)
        bottomNavigationView.visibility = View.VISIBLE
        initRecyclerView()
        return binding.root
    }

    fun initRecyclerView() {
        val recyclerView = binding.recyclerViewRecords
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = RecordAdapter(RecordProvider.recordList)
    }

}