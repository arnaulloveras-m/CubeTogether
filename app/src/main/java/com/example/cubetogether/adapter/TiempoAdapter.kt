package com.example.cubetogether.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cubetogether.databinding.ViewTiempoSingleBinding
import com.example.cubetogether.model.TiempoBO

class TiempoAdapter(private var dataSet: List<TiempoBO>, private val onClickDelete:(Int) -> Unit) : RecyclerView.Adapter<TiempoAdapter.TiempoViewHolder>() {

    inner class TiempoViewHolder(val binding: ViewTiempoSingleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TiempoBO, onClickDelete: (Int) -> Unit) {
            binding.TiempoTextView.text = item.tiempo
            binding.DataTextView.text = item.data
            binding.butoEliminar.setOnClickListener {
                onClickDelete(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiempoViewHolder {
        val binding = ViewTiempoSingleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TiempoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: TiempoViewHolder, position: Int) {
        val item = dataSet[position]
        holder.bind(item, onClickDelete)

    }

    fun updateDataSet(list: List<TiempoBO>) {
        dataSet = list
        notifyItemRangeInserted(0, itemCount)
    }

}