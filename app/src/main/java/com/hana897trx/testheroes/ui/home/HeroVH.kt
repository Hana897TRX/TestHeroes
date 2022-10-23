package com.hana897trx.testheroes.ui.home

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.hana897trx.testheroes.R
import com.hana897trx.testheroes.data.remote.heroes.model.HeroModel
import com.hana897trx.testheroes.databinding.HeroLayoutBinding
import com.hana897trx.testheroes.ui.details.DetailsFragment
import com.hana897trx.testheroes.utils.GlideUtils
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ViewComponent
import javax.inject.Inject

class HeroVH(
    private val binding: HeroLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(heroModel: HeroModel) = binding.run {
        characterName.text = heroModel.name
        characterDescription.text = heroModel.biography.firstAppearance
        GlideUtils.getInstance(binding.root.context)
            .load(heroModel.image.url)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(characterImg)

        characterCardView.setOnClickListener {
            val bundle = Bundle()
            bundle.putLong(DetailsFragment.HERO_ID, heroModel.id)
            it.findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
        }
    }
}