package com.pascaciorc.checkpoint

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import com.pascaciorc.checkpoint.data.*
import com.pascaciorc.checkpoint.databinding.FragmentInformationBinding
import com.pascaciorc.checkpoint.receiver.GeofenceBroadcastReceiver
import com.pascaciorc.checkpoint.utils.getDistance
import com.pascaciorc.checkpoint.utils.sendNotification
import com.pascaciorc.checkpoint.viewmodel.InformationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationFragment : Fragment() {

    private val args: InformationFragmentArgs by navArgs()
    private val viewModel: InformationViewModel by viewModels()
    private lateinit var binding: FragmentInformationBinding
    private lateinit var geofencingClient: GeofencingClient
    private lateinit var geofence: Geofence

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformationBinding.inflate(inflater, container, false)
        geofencingClient = LocationServices.getGeofencingClient(requireContext())

        val item = args.item

        item.distance = requireContext()
            .getDistance(args.location, item.coordinates.toLocation())

        binding.item = item

        binding.setCheckpointButton.setOnClickListener {
            setCheckpoint()
        }

        subscribeUI()
        createChannel(getString(R.string.notification_channel_id),"channel")

        return binding.root
    }

    private fun subscribeUI() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CheckpointState.Success -> {
                    geofence = createGeofence(state.id, args.item.coordinates)
                    addGeofence()
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun addGeofence() {
        geofencingClient.addGeofences(getGeofencingRequest(), geofencePendingInter).run {
            addOnSuccessListener {
                val action =
                    InformationFragmentDirections.actionInformationFragmentToDashboardFragment()
                findNavController().navigate(action)
            }
            addOnFailureListener {
                Log.e("Geofence", it.toString())
            }
        }
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
        }
    }

    private fun createGeofence(id: Long, coordinates: Coordinates): Geofence {
        return Geofence.Builder()
            .setRequestId(getString(R.string.geofence_id, id))
            .setCircularRegion(coordinates.lat, coordinates.lng, 50.0F)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .build()
    }

    private fun getGeofencingRequest(): GeofencingRequest {
        return GeofencingRequest.Builder().apply {
            setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            addGeofence(geofence)
        }.build()
    }

    private val geofencePendingInter: PendingIntent by lazy {
        val intent = Intent(requireContext(), GeofenceBroadcastReceiver::class.java)
        PendingIntent.getBroadcast(requireContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun createChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
                .apply {
                    setShowBadge(false)
                }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "This is just a test"

            val notificationManager = requireActivity().getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)

        }
    }
}