package com.example.itcoursetestapp.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.itcoursetestapp.presentation.databinding.FragmentMainContainerBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainContainerFragment : Fragment() {

    private var _binding: FragmentMainContainerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainContainerViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeContainerStates()
        setupListeners()
    }

    private fun setupListeners() {
        binding.NavMainBtn.setOnClickListener { viewModel.selectTab(MainContainerState.Home) }
        binding.NavFavoritesBtn.setOnClickListener { viewModel.selectTab(MainContainerState.Favorites) }
        binding.NavAccountBtn.setOnClickListener { viewModel.selectTab(MainContainerState.Account) }
    }

    private fun observeContainerStates() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.selectedTab.collect { state ->
                    state.apply(binding, childFragmentManager)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}