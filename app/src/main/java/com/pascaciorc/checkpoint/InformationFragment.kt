package com.pascaciorc.checkpoint

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.pascaciorc.checkpoint.databinding.FragmentInformationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationFragment: Fragment() {

    private val args: InformationFragmentArgs by navArgs()
    private lateinit var binding: FragmentInformationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformationBinding.inflate(inflater, container, false)

        val item = args.item
        val currentLocation = Location("").also {
            it.latitude = item.coordinate.lat
            it.longitude = item.coordinate.long
        }
        item.distance = "${args.location.distanceTo(currentLocation)}"

        binding.item = item

        return binding.root
    }
}