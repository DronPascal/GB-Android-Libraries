package com.pascal.sample.data

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.pascal.sample.domain.Storage
import java.io.File


/**
 * Created by dronpascal on 20.09.2021.
 */
class InternalStorageImpl(private val context: Context) : Storage {

    override fun cacheFileFromAssets(fileName: String) {
        val internalFile = File(context.filesDir, fileName)
        internalFile.writeBytes(
            context.assets
                .open(fileName, AssetManager.ACCESS_BUFFER)
                .readBytes()
        )
    }

    override fun convertFileToPngAndSave(fileName: String) {
        val internalFile = File(context.filesDir, fileName)
        val convertedFileName = internalFile.nameWithoutExtension + PNG_EXTENSION
        val convertedInternalFile = File(context.filesDir, convertedFileName)

        val out = convertedInternalFile.outputStream()
        val bmp = BitmapFactory.decodeFile(internalFile.absolutePath);
        bmp.compress(Bitmap.CompressFormat.PNG, 100, out)
        out.close()
    }

    companion object {
        const val PNG_EXTENSION = ".png"
    }

}