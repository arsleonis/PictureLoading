package com.zrz.android.pictureloading.model.network

class DownLoadManager : Download {

    override fun download(urlAddress: String, activity : DownloadTask.Callback, requestWidth : Int, requestHeight : Int): DownloadTask {
        return DownloadTask(urlAddress, activity, requestWidth, requestHeight)
    }
}