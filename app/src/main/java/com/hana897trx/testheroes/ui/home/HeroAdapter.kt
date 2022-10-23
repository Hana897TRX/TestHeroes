package com.hana897trx.testheroes.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hana897trx.testheroes.data.remote.heroes.model.HeroModel
import com.hana897trx.testheroes.databinding.HeroLayoutBinding

class HeroAdapter : ListAdapter<HeroModel, HeroVH>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroVH =
        HeroVH(
            HeroLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: HeroVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    private class DiffUtilCallback : DiffUtil.ItemCallback<HeroModel>() {
        override fun areItemsTheSame(oldItem: HeroModel, newItem: HeroModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: HeroModel, newItem: HeroModel) =
            oldItem == newItem
    }
}