package com.pascaciorc.checkpoint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.pascaciorc.checkpoint.adapter.PlacesAdapter
import com.pascaciorc.checkpoint.data.PlacesState
import com.pascaciorc.checkpoint.databinding.FragmentPlacesBinding
import com.pascaciorc.checkpoint.utils.setUp
import com.pascaciorc.checkpoint.viewmodel.PlacesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlacesFragment : Fragment() {

    private val args: PlacesFragmentArgs by navArgs()
    private val viewModel : PlacesViewModel by viewModels()
    private lateinit var binding: FragmentPlacesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlacesBinding.inflate(inflater, container, false)

        viewModel.getNearbyPlaces(args.keyword, args.location)

        binding.placesRecyclerView.setUp(requireContext())

        subscribeUI()

        return binding.root
    }

    private fun subscribeUI() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when(state) {
                is PlacesState.Result -> {
                    binding.placesRecyclerView.adapter = PlacesAdapter(state.places, args.location)
                }
                is PlacesState.Loading -> {
                    binding.loadingLayout.visibility = View.VISIBLE
                }
                is PlacesState.HideLoader -> {
                    binding.loadingLayout.visibility = View.GONE
                }
            }
        }
    }
}