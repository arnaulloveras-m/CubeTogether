package com.example.cubetogether.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cubetogether.R
import com.example.cubetogether.model.Record

class RecordViewHolder (view: View) :RecyclerView.ViewHolder(view){

    val record = view.findViewById<TextView>(R.id.idTitol)
    val scramble = view.findViewById<TextView>(R.id.idScarmble)
    val solucio = view.findViewById<TextView>(R.id.idSolve)
    val link = view.findViewById<TextView>(R.id.idLink)

    fun render(recordModel: Record) {
        record.text = recordModel.record
        scramble.text = recordModel.scramble
        solucio.text = recordModel.solucio
        link.text = recordModel.link
    }
}