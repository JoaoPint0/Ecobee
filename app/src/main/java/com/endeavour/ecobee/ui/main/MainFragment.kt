package com.endeavour.ecobee.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.endeavour.ecobee.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatButton.setOnClickListener {
            val action = MainFragmentDirections.actionDetails()
            findNavController().navigate(action)
        }

        val adapter = TasksListAdapter{ task ->
            val directions = MainFragmentDirections.actionDetails(task)
            findNavController().navigate(directions)
        }

        binding.tasksList.adapter = adapter

        viewModel.tasks.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }
}