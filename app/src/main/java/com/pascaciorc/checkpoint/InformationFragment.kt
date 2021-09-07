package com.pascaciorc.checkpoint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pascaciorc.checkpoint.data.Checkpoint
import com.pascaciorc.checkpoint.data.toCoordinates
import com.pascaciorc.checkpoint.data.toLocation
import com.pascaciorc.checkpoint.databinding.FragmentInformationBinding
import com.pascaciorc.checkpoint.utils.getDistance
import com.pascaciorc.checkpoint.viewmodel.InformationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationFragment : Fragment() {

    private val args: InformationFragmentArgs by navArgs()
    private val viewModel: InformationViewModel by viewModels()
    private lateinit var binding: FragmentInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformationBinding.inflate(inflater, container, false)

        val item = args.item

        item.distance = requireContext()
            .getDistance(args.location, item.coordinates.toLocation())

        binding.item = item

        binding.setCheckpointButton.setOnClickListener {
            setCheckpoint()
        }

        return binding.root
    }

    private fun setCheckpoint() {
        binding.item?.let {
            viewModel.setCheckpoint(
                Checkpoint(
                    it.name,
                    it.address,
                    System.currentTimeMillis(),
                    0L,
                    args.location.toCoordinates(),
                    args.item.coordinates
                )
            )
            val action = InformationFragmentDirections.actionInformationFragmentToDashboardFragment()
            findNavController().navigate(action)
        }
    }
}