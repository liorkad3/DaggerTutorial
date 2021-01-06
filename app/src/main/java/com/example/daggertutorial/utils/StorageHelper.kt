package com.example.daggertutorial.utils

import android.content.Context
import android.os.Environment
import android.os.StatFs
import android.util.Log
import java.io.File

class StorageHelper(
    val context: Context
) {
    companion object {
        const val TAG = "StorageHelper"
    }

    val internalFiles: File
    var sdFiles: File? = null

    init {
        val files = context.getExternalFilesDirs(null)

        internalFiles = files[0]
        // TODO: 06/01/2021 more than 2 sd ?
        for (i in 1 until files.size) {
            if (
                Environment.isExternalStorageRemovable(files[i]) &&
                Environment.getExternalStorageState(files[i]) == Environment.MEDIA_MOUNTED
            ) {
                sdFiles = files[i]
                Log.d(TAG, "init: found sd card - ${files[i].absolutePath}")
                break
            }
        }
    }

    val isSdAvailable
        get() =
            sdFiles != null && Environment.getExternalStorageState(sdFiles) == Environment.MEDIA_MOUNTED

    fun getAvailableBytes(file: File): Long{
        val statFs = StatFs(file.absolutePath)
        return statFs.availableBytes
    }

}