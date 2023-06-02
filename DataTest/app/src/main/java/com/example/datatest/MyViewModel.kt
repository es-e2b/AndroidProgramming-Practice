package com.example.datatest

import android.content.Context
import android.os.Environment
import androidx.lifecycle.ViewModel
import java.io.File

class MyViewModel(context: Context) : ViewModel() {
    private val fileInternal = File(context.filesDir, "appfile.txt")
    private val fileExternal =
        if (isExternalStorageMounted) // 외부 저장소 사용 가능 여부 확인 – 다음 슬라이드에 나옴
            File(context.getExternalFilesDir(null), "appfile.txt")
        else
            fileInternal

    var valueInternal: String = readValue(fileInternal)
        set(v) { field = v
            writeValue(fileInternal, v)
        }
    var valueExternal: String = readValue(fileExternal)
        set(v) { field = v
            writeValue(fileExternal, v) }
    private fun readValue(file: File) : String {
        return try {
            println("$file")
        // Internal Storage - /data/user/0/com.example.fileexample/files/appfile.txt
        // External Storage - /storage/emulated/0/Android/data/com.example.fileexample/files/appfile.txt
            file.readText(Charsets.UTF_8)
        } catch (e: Exception) {
            ""
        }
    }
    private fun writeValue(file: File, value: String) {
        file.writeText(value, Charsets.UTF_8)
    }
    private val isExternalStorageMounted: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return state == Environment.MEDIA_MOUNTED
        }
}
