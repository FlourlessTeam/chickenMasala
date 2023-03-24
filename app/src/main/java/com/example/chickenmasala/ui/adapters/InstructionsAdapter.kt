package com.example.chickenmasala.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chickenmasala.databinding.ItemInstructionsBinding

class InstructionsAdapter(private var instructions: List<String>) :
    RecyclerView.Adapter<InstructionsAdapter.InstructionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemInstructionsBinding.inflate(layoutInflater, parent, false)
        return InstructionsViewHolder(binding)
    }

    override fun getItemCount(): Int = instructions.size

    override fun onBindViewHolder(holder: InstructionsViewHolder, position: Int) =
        holder.bind(instructions[position], position + 1)



    class InstructionsViewHolder(private val binding: ItemInstructionsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(instructions: String, position: Int) {
            binding.numberCount.text = "$position."
            binding.instructions.text = instructions.trim()
        }
    }
}