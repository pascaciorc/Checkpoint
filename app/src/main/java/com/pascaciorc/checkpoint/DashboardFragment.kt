package com.pascaciorc.checkpoint

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.pascaciorc.checkpoint.adapter.CheckpointAdapter
import com.pascaciorc.checkpoint.data.toCheckpointItem
import com.pascaciorc.checkpoint.databinding.FragmentDashboardBinding
import com.pascaciorc.checkpoint.utils.setUp
import com.pascaciorc.checkpoint.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val viewModel: DashboardViewModel by viewModels()

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    requestBackgroundPermission(this.requireActivity())
                } else {
                    getLastKnownLocation()
                }
            }
        }

    private val requestBackgroundPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getLastKnownLocation()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        binding.floatingActionButton.setOnClickListener {
            requestPermission(activity as Activity)
        }

        binding.checkpointRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.HORIZONTAL
            )
        )

        binding.checkpointRecyclerView.setUp(requireContext())
        subscribeUI()

        return binding.root
    }

    private fun subscribeUI() {
        viewModel.checkpoints.observe(viewLifecycleOwner) { checkpoint ->
            binding.checkpointRecyclerView.adapter =
                CheckpointAdapter(checkpoint.map { it.toCheckpointItem(requireContext()) })
        }
    }

    private fun requestPermission(activity: Activity) {
        when {
            ContextCompat.checkSelfPermission(
                activity.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    requestBackgroundPermission(activity)
                } else {
                    getLastKnownLocation()
                }
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {

            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun requestBackgroundPermission(activity: Activity) {
        when {
            ContextCompat.checkSelfPermission(
                activity.applicationContext,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                getLastKnownLocation()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) -> {

            }
            else -> {
                requestBackgroundPermissionLauncher.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun getLastKnownLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            val action =
                DashboardFragmentDirections.actionDashboardFragmentToInputFragment(location)
            findNavController().navigate(action)
        }
    }
}