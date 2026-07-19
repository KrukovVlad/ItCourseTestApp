package com.example.itcoursetestapp.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.itcoursetestapp.presentation.databinding.FragmentFavoritesBinding
import com.example.itcoursetestapp.presentation.home.adapter.HomeDiffCallback
import com.example.itcoursetestapp.presentation.home.adapter.courseAdapterDelegate
import com.example.itcoursetestapp.presentation.home.adapter.skeletonAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    internal val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModel()
    internal val adapter = AsyncListDifferDelegationAdapter(
        HomeDiffCallback(),
        courseAdapterDelegate(
            onCourseClick = { },
            onLikeClick = { viewModel.toggleLike(it.course.id) }
        ),
        skeletonAdapterDelegate()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.FavoritesRv.adapter = adapter
        binding.FavoritesRv.itemAnimator = null

        observeState()
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    state.apply(this@FavoritesFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}