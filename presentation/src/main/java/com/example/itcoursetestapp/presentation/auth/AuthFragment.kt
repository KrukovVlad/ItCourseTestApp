package com.example.itcoursetestapp.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.itcoursetestapp.presentation.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        observeStates()
        observeEvents()
    }

    private fun setupListeners() {
        binding.EmailTextFieldEt.doOnTextChanged { text, _, _, _ ->
            viewModel.onEmailChanged(text.toString())
        }

        binding.PasswordTextFieldEt.doOnTextChanged { text, _, _, _ ->
            viewModel.onPasswordChanged(text.toString())
        }

        binding.AuthButtonBtn.setOnClickListener {
            viewModel.onLoginClicked()
        }

        binding.ButtonVkBtn.setOnClickListener {
            viewModel.onVkClicked()
        }

        binding.ButtonOkBtn.setOnClickListener {
            viewModel.onOkClicked()
        }
    }

    private fun observeStates() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    state.apply(binding)
                }
            }
        }
    }

    private fun observeEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect { event ->
                    event.apply(findNavController())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}