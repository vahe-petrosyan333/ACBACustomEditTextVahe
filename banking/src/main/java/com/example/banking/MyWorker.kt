package com.example.banking

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    val TAG = "workmng"

    companion object {
        const val KEY_WORKER = "key_worker"
    }

    @SuppressLint("SimpleDateFormat")
    override fun doWork(): Result {
        Log.i(TAG, "start")
        return try {
            val count = inputData.getInt(MainActivity.KEY_COUNT, 0)
            for (i in 0 until count) {
                Log.i("count", "uploading $i")
            }
            Log.i(TAG, "end")
            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = time.format(Date())
            val outPutData = Data.Builder()
                .putString(KEY_WORKER, currentDate)
                .build()
            Result.success(outPutData)
        } catch (e: Exception) {
            Result.failure()
        }
    }

}