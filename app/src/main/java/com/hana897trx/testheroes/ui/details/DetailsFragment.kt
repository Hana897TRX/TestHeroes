package com.hana897trx.testheroes.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.hana897trx.testheroes.R
import com.hana897trx.testheroes.data.remote.heroes.model.HeroModel
import com.hana897trx.testheroes.data.remote.heroes.model.HeroResponse
import com.hana897trx.testheroes.data.remote.heroes.model.getMapState
import com.hana897trx.testheroes.databinding.FragmentDetailsBinding
import com.hana897trx.testheroes.utils.DataState
import com.hana897trx.testheroes.utils.GlideUtils
import com.hana897trx.testheroes.utils.hide
import com.hana897trx.testheroes.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val vm: DetailsVM by viewModels()

    private var heroId: Long = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        heroId = arguments?.getLong(HERO_ID) ?: 0L
        vm.getHeroDetails(heroId)

        heroDataObserver()
    }

    private fun heroDataObserver() {
        vm.detailsState.onEach {
            when(it) {
                is DataState.Error -> { activity?.onBackPressed() }
                DataState.Loading -> { binding.detailDataProgress.show() }
                is DataState.Success -> {
                    binding.detailDataProgress.hide()
                    setViews(it.data)
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun setViews(heroModel: HeroResponse) = binding.apply {
        characterName.text = heroModel.name
        detailBtnBack.setOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }

        GlideUtils.getInstance(binding.root.context)
            .load(heroModel.image.url)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(detailCharacterImage)

        txtStats.text = heroModel.powerStats.getMapState()
        txtBiography.text = heroModel.biography.getMapState()
        txtWork.text = heroModel.work.getMapState()
        txtConnections.text = heroModel.connections.getMapState()
    }

    companion object {
        const val HERO_ID = "HERO_ID"
    }
}