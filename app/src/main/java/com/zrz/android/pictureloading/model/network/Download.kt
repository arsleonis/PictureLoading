package com.zrz.android.pictureloading.model.network

interface Download {
    fun download(urlAddress: String, activity : DownloadTask.Callback, requestWidth : Int, requestHeight : Int) : DownloadTask
}