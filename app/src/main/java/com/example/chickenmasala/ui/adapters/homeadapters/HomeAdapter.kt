package com.example.chickenmasala.ui.adapters.homeadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chickenmasala.R
import com.example.chickenmasala.ui.adapters.homeadapters.*


@Suppress("NAME_SHADOWING")
class HomeAdapter :
    ListAdapter<BaseHomeAdapter<*, *>, RecyclerView.ViewHolder>(HomeAdapterItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        BaseViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_childs_recycler_view_container, parent, false)
        )
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder=holder as BaseViewHolder
        when (position) {

            0 -> (holder).bind(getItem(0)as HomeCuisinesAdapter)
            1 -> (holder).bind(getItem(1)as SpecialRecipesAdapter)
            2 -> (holder).bind(getItem(2) as FastRecipesAdapter)
            3 -> (holder).bind(getItem(3)as EasyRecipesAdapter)

        }
    }
    override fun getItemViewType(position: Int) = position

     class BaseViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(adapter: BaseHomeAdapter<*, *>) {
            itemView.findViewById<RecyclerView>(R.id.child_recycler_view).adapter = adapter
            itemView.findViewById<TextView>(R.id.container_title).text = adapter.containerTitle
            itemView.findViewById<TextView>(R.id.viewAll).setOnClickListener(adapter::handleViewAllTransition)

        }
    }



    class HomeAdapterItemDiffCallback : DiffUtil.ItemCallback<BaseHomeAdapter<*, *>>() {
        override fun areItemsTheSame(oldItem: BaseHomeAdapter<*, *>, newItem: BaseHomeAdapter<*, *>): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: BaseHomeAdapter<*, *>,
            newItem: BaseHomeAdapter<*, *>
        ): Boolean {
          return areItemsTheSame(oldItem,newItem)
        }
    }


}

