package com.pascaciorc.checkpoint.worker

import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.pascaciorc.checkpoint.data.AppDatabase
import com.pascaciorc.checkpoint.utils.sendNotification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeofenceWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager

        val id = inputData.getLong(GEO_ID, -1)

        val appDatabase = AppDatabase.getInstance(applicationContext)
        val dao = appDatabase.checkpointDao()
        val checkpoint = dao.getCheckpoint(id)
        val result = dao.updateCheckpoint(id, System.currentTimeMillis())

        if (result > 0) {
            notificationManager.sendNotification(
                "You arrived to your destination: ${checkpoint.name}",
                applicationContext
            )

            Log.d(TAG, "Updated $result rows")
            Result.success()
        } else {
            Log.e(TAG, "Error: $result")
            Result.failure()
        }
    }


    companion object {
        private const val TAG = "GeofenceWorker"
        const val GEO_ID = "ID"
    }
}