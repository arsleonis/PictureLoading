package com.zrz.android.pictureloading.model.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadTask(urlAddress: String, activity : Callback, width : Int, height : Int) : AsyncTask<URL, Bitmap?, Void>() {

    interface Callback{
        fun callingBack(bitmap : Bitmap?)
    }

    private val requestWidth = width
    private val requestHeight = height
    private val callback = activity
    private val type = urlAddress

    override fun doInBackground(vararg params: URL?): Void? {
        val url = URL(type)
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val input = BufferedInputStream(urlConnection.inputStream)
            val byteArray = input.readBytes()
            val finalBmp = decodeSampledBitmapFromResource(requestWidth, requestHeight, byteArray)
            publishProgress(finalBmp)
        } finally {
            urlConnection.disconnect()
        }
        return null
    }

    override fun onProgressUpdate(vararg values: Bitmap?) {
        super.onProgressUpdate(*values)
        callback.callingBack(values[0])
    }

    private fun decodeSampledBitmapFromResource(reqWidth: Int, reqHeight: Int, byteArray: ByteArray): Bitmap {
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true
            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size, this)
            inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)
            inJustDecodeBounds = false
            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size, this)
        }
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}