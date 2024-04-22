package com.example.cubetogether.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cubetogether.R
import com.example.cubetogether.model.Record

class RecordAdapter(val recordList: List<Record>) : RecyclerView.Adapter<RecordViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecordViewHolder(layoutInflater.inflate(R.layout.item_record, parent, false))
    }

    override fun getItemCount(): Int = recordList.size

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val item = recordList[position]
        holder.render(item)
    }
}