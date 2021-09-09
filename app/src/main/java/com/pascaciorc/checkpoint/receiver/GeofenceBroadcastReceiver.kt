package com.pascaciorc.checkpoint.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent
import com.pascaciorc.checkpoint.worker.GeofenceWorker
import com.pascaciorc.checkpoint.worker.GeofenceWorker.Companion.GEO_ID

class GeofenceBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent)

        if(geofencingEvent.hasError()) {
            val errorMessage = GeofenceStatusCodes.getStatusCodeString(geofencingEvent.errorCode)
            Log.e(TAG, errorMessage)
            return
        }

        val geofenceTransition = geofencingEvent.geofenceTransition

        if(geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            geofencingEvent.triggeringGeofences.forEach { geofence ->
                val id = geofence.requestId.removePrefix(PREFIX).toLong()
                val inputData = Data.Builder()
                    .putLong(GEO_ID, id)
                    .build()

                val workRequest = OneTimeWorkRequestBuilder<GeofenceWorker>()
                    .setInputData(inputData)
                    .build()

                WorkManager
                    .getInstance(context)
                    .enqueue(workRequest)

                Log.d(TAG, geofence.toString())
            }
        }
    }

    companion object {
        const val TAG = "BroadcastReceiver"
        private const val PREFIX = "GEO"
    }
}