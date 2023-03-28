package com.example.chickenmasala.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chickenmasala.databinding.ItemHistoryBinding
import com.example.chickenmasala.entities.History

class HistoryAdapter(private val histories: List<History>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(layoutInflater, parent, false)
        return HistoryViewHolder(binding)
    }

    override fun getItemCount(): Int = histories.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) =
        holder.bind(histories[position])


    class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {
            binding.textContentHistory.text = history.history
            binding.imageHistory.setImageResource(history.image)
        }
    }
}