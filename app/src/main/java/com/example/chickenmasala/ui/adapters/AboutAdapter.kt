package com.example.chickenmasala.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chickenmasala.databinding.ItemChefListBinding
import com.example.chickenmasala.databinding.ItemHistoryListBinding
import com.example.chickenmasala.databinding.ItemImageBinding
import com.example.chickenmasala.databinding.ItemTextBinding
import com.example.chickenmasala.entities.AboutItem
import com.example.chickenmasala.entities.Chef
import com.example.chickenmasala.entities.History
import com.example.chickenmasala.entities.enums.AboutItemType

class AboutAdapter(private val items: List<AboutItem<Any>>) :
    RecyclerView.Adapter<AboutAdapter.BaseAboutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseAboutViewHolder {
        return when (viewType) {
            VIEW_TYPE_IMAGE -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemImageBinding.inflate(layoutInflater, parent, false)
                ImageViewHolder(binding)
            }
            VIEW_TYPE_CHEFS -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemChefListBinding.inflate(layoutInflater, parent, false)
                ChefsViewHolder(binding)
            }
            VIEW_TYPE_CHEF_TEXT -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTextBinding.inflate(layoutInflater, parent, false)
                ChefTextViewHolder(binding)
            }
            VIEW_TYPE_HISTORY_TEXT -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTextBinding.inflate(layoutInflater, parent, false)
                HistoryTextViewHolder(binding)
            }
            VIEW_TYPE_HISTORY -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHistoryListBinding.inflate(layoutInflater, parent, false)
                HistoryViewHolder(binding)
            }
            else -> throw Exception("Unknown View Type")

        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseAboutViewHolder, position: Int) {
        when (holder) {
            is ImageViewHolder -> bindImage(holder, position)
            is ChefsViewHolder -> bindChef(holder, position)
            is ChefTextViewHolder -> bindChefText(holder, position)
            is HistoryTextViewHolder -> bindHistoryText(holder, position)
            is HistoryViewHolder -> bindHistory(holder, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            AboutItemType.TYPE_IMAGE -> VIEW_TYPE_IMAGE
            AboutItemType.TYPE_CHEFS -> VIEW_TYPE_CHEFS
            AboutItemType.TYPE_CHEF_TEXT -> VIEW_TYPE_CHEF_TEXT
            AboutItemType.TYPE_HISTORY_TEXT -> VIEW_TYPE_HISTORY_TEXT
            AboutItemType.TYPE_HISTORY -> VIEW_TYPE_HISTORY
        }
    }

    private fun bindImage(holder: BaseAboutViewHolder, position: Int) {
        val image = items[position].item as Int
        (holder as ImageViewHolder).bind(image)
    }


    private fun bindChef(holder: BaseAboutViewHolder, position: Int) {
        val chefs = items[position].item as List<Chef>
        (holder as ChefsViewHolder).bind(chefs)
    }


    private fun bindChefText(holder: BaseAboutViewHolder, position: Int) {
        val textChef = items[position].item as String
        (holder as ChefTextViewHolder).bind(textChef)
    }

    private fun bindHistoryText(holder: BaseAboutViewHolder, position: Int) {
        val textHistory = items[position].item as String
        (holder as HistoryTextViewHolder).bind(textHistory)
    }

    private fun bindHistory(holder: BaseAboutViewHolder, position: Int) {
        val histories = items[position].item as List<History>
        (holder as HistoryViewHolder).bind(histories)
    }


    abstract class BaseAboutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ImageViewHolder(private val binding: ItemImageBinding) :
        BaseAboutViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.imageAbout.setImageResource(image)
        }
    }

    class ChefsViewHolder(private val binding: ItemChefListBinding) :
        BaseAboutViewHolder(binding.root) {
        fun bind(chefs: List<Chef>) {
            val adapter = ChefAdapter(chefs)
            binding.chefRecycler.adapter = adapter
        }
    }

    class ChefTextViewHolder(private val binding: ItemTextBinding) :
        BaseAboutViewHolder(binding.root) {
        fun bind(textChef: String) {
            binding.aboutHeadline.text = textChef
        }
    }

    class HistoryTextViewHolder(private val binding: ItemTextBinding) :
        BaseAboutViewHolder(binding.root) {
        fun bind(textHistory: String) {
            binding.aboutHeadline.text = textHistory
        }
    }

    class HistoryViewHolder(private val binding: ItemHistoryListBinding) :
        BaseAboutViewHolder(binding.root) {
        fun bind(histories: List<History>) {
            val adapter = HistoryAdapter(histories)
            binding.historyRecycler.adapter = adapter
        }
    }

    companion object {
        private const val VIEW_TYPE_IMAGE = 1
        private const val VIEW_TYPE_CHEFS = 2
        private const val VIEW_TYPE_CHEF_TEXT = 3
        private const val VIEW_TYPE_HISTORY_TEXT = 4
        private const val VIEW_TYPE_HISTORY = 5
    }


}