package com.endeavour.ecobee.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.endeavour.ecobee.databinding.DetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    private val detailsViewModel: DetailsViewModel by viewModels()

    private lateinit var binding: DetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater).apply {
            viewModel = detailsViewModel
            lifecycleOwner = this@DetailsFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        args.task?.let {
            detailsViewModel.editTask(it)
        }

        binding.taskSaveBtn.setOnClickListener {
            detailsViewModel.saveTask()
            findNavController().popBackStack()
        }
    }
}