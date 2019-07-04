package com.zrz.android.pictureloading.core

import android.app.Application
import com.zrz.android.pictureloading.model.network.DownLoadManager
import com.zrz.android.pictureloading.model.network.Download

class App : Application() {
    val downloader: Download = DownLoadManager()
}