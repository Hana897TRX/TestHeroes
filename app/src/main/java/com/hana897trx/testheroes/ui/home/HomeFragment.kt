package com.hana897trx.testheroes.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import com.hana897trx.testheroes.R
import com.hana897trx.testheroes.databinding.FragmentHomeBinding
import com.hana897trx.testheroes.utils.DataState
import com.hana897trx.testheroes.utils.hide
import com.hana897trx.testheroes.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val vm: HomeVM by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter: HeroAdapter = HeroAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        observeHeroData()
    }

    private fun setViews() {
        binding.rvHero.adapter = adapter
        rvObserver()
    }

    private fun observeHeroData() {
        vm.allHeroesState.onEach { response ->
            when(response) {
                is DataState.Error -> { binding.progressBar.hide() }
                is DataState.Loading -> { binding.rvHero.hide() }
                is DataState.Success -> {
                    binding.progressBar.hide()
                    binding.rvHero.show()
                    adapter.submitList(response.data)
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun rvObserver() {
        binding.rvHero.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1)) {
                    vm.requestNextFive()
                }
            }
        })
    }
}