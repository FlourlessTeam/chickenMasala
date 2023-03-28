package com.example.chickenmasala.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chickenmasala.databinding.ItemChefBinding
import com.example.chickenmasala.entities.Chef

class ChefAdapter(private var chefs: List<Chef>) :
    RecyclerView.Adapter<ChefAdapter.ChefViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChefViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemChefBinding.inflate(layoutInflater, parent, false)
        return ChefViewHolder(binding)
    }

    override fun getItemCount(): Int = chefs.size

    override fun onBindViewHolder(holder: ChefViewHolder, position: Int) =
        holder.bind(chefs[position])


    class ChefViewHolder(private val binding: ItemChefBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chef: Chef) {
            binding.imageChef.setImageResource(chef.image)
            binding.textChef.text = chef.name
        }
    }


}
